package com.eratart.baristashandbook.presentation.disheslist.di

import com.eratart.baristashandbook.presentation.disheslist.viewmodel.DishesListViewModel
import org.koin.dsl.module

val dishesListModule = module {
    single { DishesListViewModel(get(), get()) }
}