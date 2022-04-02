package com.eratart.baristashandbook.domain.preferences

interface IFavoritesPreferences {
    companion object {
        const val FAVORITES = "IFavoritesPreferences.FAVORITES"
    }

    fun getAllFavorites(): List<String>

    fun checkIsFavorite(id: String): Boolean

    fun addToFavorites(id: String)

    fun updateFavoritesList(list: List<String>)

    fun removeFromFavorites(id: String)

    fun clearFavorites()
}