package com.eratart.baristashandbook.domain.cache

import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory

interface IAppCache {

    fun getDishes(): List<Dish>

    fun getItems(): List<Item>

    fun getItemsCategories(): List<ItemCategory>

    fun storeDishes(list: List<Dish>)

    fun storeItems(list: List<Item>)

    fun storeItemsCategories(list: List<ItemCategory>)

    fun initCache()

    fun clearCache()
}