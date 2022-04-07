package com.eratart.baristashandbook.domain.interactor.favorites

import com.eratart.baristashandbook.domain.model.Item

interface IFavoritesInteractor {

    fun getAllFavorites(): List<Item>
    fun checkIsFavorite(item: Item): Boolean
    fun addToFavorites(item: Item)
    fun updateFavoritesList(list: List<Item>)
    fun removeFromFavorites(item: Item)
    fun clearFavorites()

}