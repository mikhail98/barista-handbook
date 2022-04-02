package com.eratart.baristashandbook.domain.cache

import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item

interface IAppCache {

    fun getDishes(): List<Dish>

    fun getItems(): List<Item>

    fun storeDishes(list: List<Dish>)

    fun storeItems(list: List<Item>)

    fun initCache()

    fun clearCache()
}