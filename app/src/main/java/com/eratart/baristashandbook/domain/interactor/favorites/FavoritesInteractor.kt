package com.eratart.baristashandbook.domain.interactor.favorites

import com.eratart.baristashandbook.domain.preferences.IFavoritesPreferences

class FavoritesInteractor(private val favoritesPreferences: IFavoritesPreferences) :
    IFavoritesInteractor {

    override fun getAllFavorites(): List<String> {
        return favoritesPreferences.getAllFavorites()
    }

    override fun checkIsFavorite(id: String): Boolean {
        return favoritesPreferences.checkIsFavorite(id)
    }

    override fun addToFavorites(id: String) {
        favoritesPreferences.addToFavorites(id)
    }

    override fun updateFavoritesList(list: List<String>) {
        favoritesPreferences.updateFavoritesList(list)
    }

    override fun removeFromFavorites(id: String) {
        favoritesPreferences.removeFromFavorites(id)
    }

    override fun clearFavorites() {
        favoritesPreferences.clearFavorites()
    }
}