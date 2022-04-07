package com.eratart.baristashandbook.presentation.favorites.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.eratart.baristashandbook.domain.interactor.favorites.IFavoritesInteractor
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.preferences.IAppPreferences

class FavoritesViewModel(
    private val favoritesInteractor: IFavoritesInteractor,
    appPreferences: IAppPreferences
) : BaseViewModel(appPreferences) {

    private val _favoritesList = MutableLiveData<List<Item>>()
    val favoritesList: LiveData<List<Item>> = _favoritesList

    fun fetchFavorites() {
        _favoritesList.postValue(favoritesInteractor.getAllFavorites())
    }

    fun removeFromFavorites(item: Item) {
        favoritesInteractor.removeFromFavorites(item)
    }
}