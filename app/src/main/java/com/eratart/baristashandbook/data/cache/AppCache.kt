package com.eratart.baristashandbook.data.cache

import com.eratart.baristashandbook.domain.cache.IAppCache
import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.repository.IDishesRepo
import com.eratart.baristashandbook.domain.repository.IItemsRepo

class AppCache(
    private val dishesRepo: IDishesRepo,
    private val itemsRepo: IItemsRepo
) : IAppCache {

    private val cachedDishes = mutableListOf<Dish>()
    private val cachedItems = mutableListOf<Item>()

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

    override fun storeDishes(list: List<Dish>) {
        cachedDishes.clear()
        cachedDishes.addAll(list)
    }

    override fun storeItems(list: List<Item>) {
        cachedItems.clear()
        cachedItems.addAll(list)
    }

    override fun clearCache() {
        cachedItems.clear()
        cachedDishes.clear()
    }

    override fun initCache() {
        getDishes()
        getItems()
    }
}