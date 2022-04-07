package com.eratart.baristashandbook.presentation.itemdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.eratart.baristashandbook.domain.interactor.favorites.IFavoritesInteractor
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.preferences.IAppPreferences

class ItemDetailsViewModel(
    private val favoritesInteractor: IFavoritesInteractor,
    appPreferences: IAppPreferences
) : BaseViewModel(appPreferences) {

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun fetchIsFavorite(item: Item?) {
        item ?: return
        _isFavorite.postValue(favoritesInteractor.checkIsFavorite(item))
    }

    fun markFavorite(item: Item, isFavorite: Boolean) {
        if (isFavorite) {
            addToFavorites(item)
        } else {
            removeFromFavorites(item)
        }
    }

    private fun addToFavorites(item: Item) {
        favoritesInteractor.addToFavorites(item)
    }

    private fun removeFromFavorites(item: Item) {
        favoritesInteractor.removeFromFavorites(item)
    }

}