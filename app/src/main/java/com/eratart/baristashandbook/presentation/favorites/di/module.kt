package com.eratart.baristashandbook.presentation.favorites.di

import com.eratart.baristashandbook.presentation.favorites.viewmodel.FavoritesViewModel
import org.koin.dsl.module

val favoritesModule = module {
    single { FavoritesViewModel(get(), get(), get()) }
}