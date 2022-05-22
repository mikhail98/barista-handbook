package com.eratart.baristashandbook.presentation.itemscategorieslist.di

import com.eratart.baristashandbook.presentation.itemscategorieslist.viewmodel.ItemsCategoriesListViewModel
import org.koin.dsl.module

val itemsCategoriesListModule = module {
    single { ItemsCategoriesListViewModel(get(), get()) }
}