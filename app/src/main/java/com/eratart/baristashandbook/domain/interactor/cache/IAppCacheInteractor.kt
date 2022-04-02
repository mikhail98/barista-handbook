package com.eratart.baristashandbook.domain.interactor.cache

import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item

interface IAppCacheInteractor {
    fun getDishes(): List<Dish>

    fun getItems(): List<Item>

    fun initCache()

    fun clearCache()
}