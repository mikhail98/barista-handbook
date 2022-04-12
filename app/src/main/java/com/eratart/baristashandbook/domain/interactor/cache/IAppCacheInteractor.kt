package com.eratart.baristashandbook.domain.interactor.cache

import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory

interface IAppCacheInteractor {
    fun getItemCategories(): List<ItemCategory>

    fun getDishes(): List<Dish>

    fun getItems(): List<Item>

    fun initCache()

    fun clearCache()
}