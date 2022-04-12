package com.eratart.baristashandbook.data.cache

import com.eratart.baristashandbook.core.ext.replaceAllWith
import com.eratart.baristashandbook.domain.cache.IAppCache
import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory
import com.eratart.baristashandbook.domain.repository.IDishesRepo
import com.eratart.baristashandbook.domain.repository.IItemCategoriesRepo
import com.eratart.baristashandbook.domain.repository.IItemsRepo

class AppCache(
    private val dishesRepo: IDishesRepo,
    private val itemsRepo: IItemsRepo,
    private val itemsCategoriesRepo: IItemCategoriesRepo
) : IAppCache {

    private val cachedDishes = mutableListOf<Dish>()
    private val cachedItems = mutableListOf<Item>()
    private val cachedCategories = mutableListOf<ItemCategory>()

    override fun getDishes(): List<Dish> {
        if (cachedDishes.isEmpty()) {
            storeDishes(dishesRepo.getDishes())
        }
        return cachedDishes
    }

    override fun getItems(): List<Item> {
        if (cachedItems.isEmpty()) {
            storeItems(itemsRepo.getItems())
        }
        return cachedItems
    }

    override fun getItemsCategories(): List<ItemCategory> {
        if (cachedCategories.isEmpty()) {
            storeItemsCategories(itemsCategoriesRepo.getItemCategories())
        }

        return cachedCategories
    }

    override fun storeItemsCategories(list: List<ItemCategory>) {
        val drinks = getItems()

        val newCategoriesList = list.map { itemCategory ->
            val categoryDrinks = drinks.filter { drink -> drink.categoryId == itemCategory.id }
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

    override fun clearCache() {
        cachedItems.replaceAllWith(emptyList())
        cachedDishes.replaceAllWith(emptyList())
        cachedCategories.replaceAllWith(emptyList())
    }

    override fun initCache() {
        getDishes()
        getItems()
        getItemsCategories()
    }
}