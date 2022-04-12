package com.eratart.baristashandbook.presentation.favorites.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.eratart.baristashandbook.domain.interactor.cache.IAppCacheInteractor
import com.eratart.baristashandbook.domain.interactor.favorites.IFavoritesInteractor
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory
import com.eratart.baristashandbook.domain.preferences.IAppPreferences

class FavoritesViewModel(
    private val appCacheInteractor: IAppCacheInteractor,
    private val favoritesInteractor: IFavoritesInteractor,
    appPreferences: IAppPreferences
) : BaseViewModel(appPreferences) {

    private val _favoritesList = MutableLiveData<List<Item>>()
    val favoritesList: LiveData<List<Item>> = _favoritesList

    private val _itemCategoriesFromCache = MutableLiveData<List<ItemCategory>>()
    val itemCategoriesFromCache: LiveData<List<ItemCategory>> = _itemCategoriesFromCache

    override fun onCreate() {
        fetchFavorites()
    }

    private fun fetchCategories() {
        _itemCategoriesFromCache.postValue(appCacheInteractor.getItemCategories())
    }

    fun fetchFavorites() {
        _favoritesList.postValue(favoritesInteractor.getAllFavorites())
    }

    fun removeFromFavorites(item: Item) {
        favoritesInteractor.removeFromFavorites(item)
    }
}