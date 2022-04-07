package com.eratart.baristashandbook.domain.preferences

import com.eratart.baristashandbook.domain.model.Item

interface IFavoritesPreferences {
    companion object {
        const val FAVORITES = "IFavoritesPreferences.FAVORITES"
    }

    fun getAllFavorites(): List<Item>

    fun checkIsFavorite(item: Item): Boolean

    fun addToFavorites(item: Item)

    fun updateFavoritesList(list: List<Item>)

    fun removeFromFavorites(item: Item)

    fun clearFavorites()
}