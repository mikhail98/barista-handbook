package com.eratart.baristashandbook.domain.interactor.favorites

import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.preferences.IFavoritesPreferences

class FavoritesInteractor(private val favoritesPreferences: IFavoritesPreferences) :
    IFavoritesInteractor {

    override fun getAllFavorites(): List<Item> {
        return favoritesPreferences.getAllFavorites()
    }

    override fun checkIsFavorite(item: Item): Boolean {
        return favoritesPreferences.checkIsFavorite(item)
    }

    override fun addToFavorites(item: Item) {
        favoritesPreferences.addToFavorites(item)
    }

    override fun updateFavoritesList(list: List<Item>) {
        favoritesPreferences.updateFavoritesList(list)
    }

    override fun removeFromFavorites(item: Item) {
        favoritesPreferences.removeFromFavorites(item)
    }

    override fun clearFavorites() {
        favoritesPreferences.clearFavorites()
    }
}