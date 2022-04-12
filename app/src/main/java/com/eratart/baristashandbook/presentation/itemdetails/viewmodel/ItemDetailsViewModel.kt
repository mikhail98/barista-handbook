package com.eratart.baristashandbook.presentation.itemdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.eratart.baristashandbook.domain.interactor.cache.IAppCacheInteractor
import com.eratart.baristashandbook.domain.interactor.favorites.IFavoritesInteractor
import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory
import com.eratart.baristashandbook.domain.preferences.IAppPreferences

class ItemDetailsViewModel(
    private val appCacheInteractor: IAppCacheInteractor,
    private val favoritesInteractor: IFavoritesInteractor,
    appPreferences: IAppPreferences
) : BaseViewModel(appPreferences) {

    private val _initData = MutableLiveData<Triple<Boolean, Dish, ItemCategory>>()
    val initData: LiveData<Triple<Boolean, Dish, ItemCategory>> = _initData

    fun fetchIsFavorite(item: Item?) {
        item ?: return
        val isFavorite = favoritesInteractor.checkIsFavorite(item)
        val dish = appCacheInteractor.getDishes()
            .find { dish -> dish.id == item.dishId } ?: return
        val category = appCacheInteractor.getItemCategories()
            .find { category -> category.id == item.categoryId } ?: return
        _initData.postValue(Triple(isFavorite, dish, category))
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