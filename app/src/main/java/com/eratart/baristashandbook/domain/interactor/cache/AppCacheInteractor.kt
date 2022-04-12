package com.eratart.baristashandbook.domain.interactor.cache

import com.eratart.baristashandbook.domain.cache.IAppCache
import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory

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

    override fun initCache() {
        appCache.initCache()
    }

    override fun clearCache() {
        appCache.clearCache()
    }
}