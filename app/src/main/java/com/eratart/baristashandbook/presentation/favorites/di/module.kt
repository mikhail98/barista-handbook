package com.eratart.baristashandbook.presentation.favorites.di

import com.eratart.baristashandbook.presentation.favorites.view.FavoritesActivity
import com.eratart.baristashandbook.presentation.favorites.viewmodel.FavoritesViewModel
import org.koin.dsl.module

val favoritesModule = module {
    scope<FavoritesActivity> {
        scoped { FavoritesViewModel(get(), get(), get()) }
    }
}