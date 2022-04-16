package com.eratart.baristashandbook.domain.cache

import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory
import com.eratart.baristashandbook.domain.model.NewsBot
import kotlinx.coroutines.flow.Flow

interface IAppCache {

    fun getDishes(): List<Dish>

    fun getNews(): List<NewsBot>

    fun getItems(): List<Item>

    fun getItemsCategories(): List<ItemCategory>

    fun storeNews(list: List<NewsBot>)

    fun storeDishes(list: List<Dish>)

    fun storeItems(list: List<Item>)

    fun storeItemsCategories(list: List<ItemCategory>)

    suspend fun initCache(): Flow<Boolean>

    fun clearCache()
}