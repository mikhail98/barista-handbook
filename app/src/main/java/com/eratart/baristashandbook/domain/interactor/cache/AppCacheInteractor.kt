package com.eratart.baristashandbook.domain.interactor.cache

import com.eratart.baristashandbook.domain.cache.IAppCache
import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory
import com.eratart.baristashandbook.domain.model.NewsBot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn

class AppCacheInteractor(private val appCache: IAppCache) : IAppCacheInteractor {

    override fun getItemCategories(): List<ItemCategory> {
        return appCache.getItemsCategories()
    }

    override fun getDishes(): List<Dish> {
        return appCache.getDishes()
    }

    override fun getItems(): List<Item> {
        return appCache.getItems()
    }

    override fun getNews(): List<NewsBot> {
        return appCache.getNews()
    }

    override suspend fun initCache(): Flow<Boolean> {
        return appCache.initCache()
    }

    override fun clearCache() {
        appCache.clearCache()
    }
}