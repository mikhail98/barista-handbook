package com.eratart.baristashandbook.data.preferencnes.impl

import android.content.SharedPreferences
import com.eratart.baristashandbook.data.preferencnes.BasePreferences
import com.eratart.baristashandbook.domain.model.FavoriteItems
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.preferences.IFavoritesPreferences

class FavoritesPreferences(sharedPreferences: SharedPreferences) :
    BasePreferences(sharedPreferences), IFavoritesPreferences {

    private fun getFavoritesKey() = IFavoritesPreferences.FAVORITES

    @Suppress("UNCHECKED_CAST")
    override fun getAllFavorites(): List<Item> {
        return getObject(getFavoritesKey(), FavoriteItems(), FavoriteItems::class.java).items
    }

    override fun checkIsFavorite(item: Item): Boolean {
        val favorites = getAllFavorites()
        return favorites.map { favorite -> favorite.id }.contains(item.id)
    }

    override fun addToFavorites(item: Item) {
        val favorites = ArrayList(getAllFavorites())

        if (checkIsFavorite(item)) return
        favorites.add(item)
        updateFavoritesList(favorites)
    }

    override fun updateFavoritesList(list: List<Item>) {
        saveObject(getFavoritesKey(), FavoriteItems(list))
    }

    override fun removeFromFavorites(item: Item) {
        val favorites = ArrayList(getAllFavorites())

        if (!checkIsFavorite(item)) return
        favorites.removeAll { favorite -> favorite.id == item.id }
        updateFavoritesList(favorites)
    }

    override fun clearFavorites() {
        updateFavoritesList(listOf())
    }
}