package com.eratart.baristashandbook.data.cache

import com.eratart.baristashandbook.core.ext.replaceAllWith
import com.eratart.baristashandbook.domain.cache.IAppCache
import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory
import com.eratart.baristashandbook.domain.model.NewsBot
import com.eratart.baristashandbook.domain.repository.IDishesRepo
import com.eratart.baristashandbook.domain.repository.IItemCategoriesRepo
import com.eratart.baristashandbook.domain.repository.IItemsRepo
import com.eratart.baristashandbook.domain.repository.INewsRepo
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.*

class AppCache(
    private val dishesRepo: IDishesRepo,
    private val newsRepo: INewsRepo,
    private val itemsRepo: IItemsRepo,
    private val itemsCategoriesRepo: IItemCategoriesRepo,
    private val firebaseDatabase: FirebaseDatabase
) : IAppCache {

    private val cachedItems = mutableListOf<Item>()
    private val cachedDishes = mutableListOf<Dish>()
    private val cachedNews = mutableListOf<NewsBot>()
    private val cachedCategories = mutableListOf<ItemCategory>()

    override fun getNews(): List<NewsBot> {
        return cachedNews
    }

    override fun getDishes(): List<Dish> {
        return cachedDishes
    }

    override fun getItems(): List<Item> {
        return cachedItems
    }

    override fun getItemsCategories(): List<ItemCategory> {
        return cachedCategories
    }

    override fun storeItemsCategories(list: List<ItemCategory>) {
        val drinks = getItems()

        val newCategoriesList = list.map { itemCategory ->
            val categoryDrinks = drinks
                .filter { drink -> drink.categoryIdList.contains(itemCategory.id) }
                .sortedBy { it.title }
            ItemCategory(itemCategory.id, itemCategory.title, categoryDrinks)
        }

        cachedCategories.replaceAllWith(newCategoriesList)
    }

    override fun storeDishes(list: List<Dish>) {
        cachedDishes.replaceAllWith(list)
    }

    override fun storeItems(list: List<Item>) {
        cachedItems.replaceAllWith(list)
    }

    override fun storeNews(list: List<NewsBot>) {
        cachedNews.replaceAllWith(list)
    }

    override fun clearCache() {
        cachedNews.replaceAllWith(emptyList())
        cachedItems.replaceAllWith(emptyList())
        cachedDishes.replaceAllWith(emptyList())
        cachedCategories.replaceAllWith(emptyList())
    }

    override suspend fun initCache(): Flow<Boolean> {
        val zip1 = newsRepo.getNews()
            .zip(dishesRepo.getDishes()) { news, dishes ->
                Pair(news, dishes)
            }

        val zip2 = itemsRepo.getItems()
            .zip(itemsCategoriesRepo.getItemCategories()) { items, itemCategories ->
                Pair(items, itemCategories)
            }
        return zip1.zip(zip2) { data1, data2 ->
            Pair(data1, data2)
        }
            .map { data ->
                storeNews(data.first.first)
                storeDishes(data.first.second)
                storeItems(data.second.first)
                storeItemsCategories(data.second.second)
                true
            }
            .onCompletion { firebaseDatabase.goOffline() }
            .catch {
                emit(false)
            }
    }
}