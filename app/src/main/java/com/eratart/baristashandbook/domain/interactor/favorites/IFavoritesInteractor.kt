package com.eratart.baristashandbook.domain.interactor.favorites

interface IFavoritesInteractor {

    fun getAllFavorites(): List<String>
    fun checkIsFavorite(id: String): Boolean
    fun addToFavorites(id: String)
    fun updateFavoritesList(list: List<String>)
    fun removeFromFavorites(id: String)
    fun clearFavorites()

}