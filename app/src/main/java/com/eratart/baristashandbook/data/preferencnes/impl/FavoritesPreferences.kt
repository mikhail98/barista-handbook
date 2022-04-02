package com.eratart.baristashandbook.data.preferencnes.impl

import android.content.SharedPreferences
import com.eratart.baristashandbook.data.preferencnes.BasePreferences
import com.eratart.baristashandbook.domain.preferences.IFavoritesPreferences

class FavoritesPreferences(sharedPreferences: SharedPreferences) :
    BasePreferences(sharedPreferences), IFavoritesPreferences {

    private fun getFavoritesKey() = IFavoritesPreferences.FAVORITES

    @Suppress("UNCHECKED_CAST")
    override fun getAllFavorites(): List<String> {
        return getObject(getFavoritesKey(), listOf<String>(), List::class.java) as List<String>
    }

    override fun checkIsFavorite(id: String): Boolean {
        val favorites = getAllFavorites()
        return favorites.contains(id)
    }

    override fun addToFavorites(id: String) {
        val favorites = ArrayList(getAllFavorites())

        if (checkIsFavorite(id)) return
        favorites.add(id)
        updateFavoritesList(favorites)
    }

    override fun updateFavoritesList(list: List<String>) {
        saveObject(getFavoritesKey(), list)
    }

    override fun removeFromFavorites(id: String) {
        val favorites = ArrayList(getAllFavorites())

        if (!checkIsFavorite(id)) return
        favorites.remove(id)
        updateFavoritesList(favorites)
    }

    override fun clearFavorites() {
        saveObject(getFavoritesKey(), emptyList<String>())
    }
}