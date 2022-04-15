package com.eratart.baristashandbook.presentation.favorites.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.eratart.baristashandbook.domain.interactor.cache.IAppCacheInteractor
import com.eratart.baristashandbook.domain.interactor.favorites.IFavoritesInteractor
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory
import com.eratart.baristashandbook.domain.preferences.IAppPreferences
import com.eratart.baristashandbook.tools.resources.IResourceManager

class FavoritesViewModel(
    private val favoritesInteractor: IFavoritesInteractor,
    resourceManager: IResourceManager,
    appPreferences: IAppPreferences
) : BaseViewModel(resourceManager, appPreferences) {

    private val _favoritesList = MutableLiveData<List<Item>>()
    val favoritesList: LiveData<List<Item>> = _favoritesList

    override fun onCreate() {
        fetchFavorites()
    }

    fun fetchFavorites() {
        _favoritesList.postValue(favoritesInteractor.getAllFavorites())
    }

    fun removeFromFavorites(item: Item) {
        favoritesInteractor.removeFromFavorites(item)
    }
}