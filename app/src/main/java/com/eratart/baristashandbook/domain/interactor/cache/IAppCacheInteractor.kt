package com.eratart.baristashandbook.domain.interactor.cache

import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory
import com.eratart.baristashandbook.domain.model.NewsBot
import kotlinx.coroutines.flow.Flow

interface IAppCacheInteractor {
    fun getItemCategories(): List<ItemCategory>

    fun getDishes(): List<Dish>

    fun getItems(): List<Item>

    fun getNews(): List<NewsBot>

    suspend fun initCache(): Flow<Boolean>

    fun clearCache()
}