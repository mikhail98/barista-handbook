package com.eratart.baristashandbook.presentation.disheslist.di

import com.eratart.baristashandbook.presentation.disheslist.view.DishesListActivity
import com.eratart.baristashandbook.presentation.disheslist.viewmodel.DishesListViewModel
import org.koin.dsl.module

val dishesListModule = module {
    scope<DishesListActivity> {
        scoped { DishesListViewModel(get()) }
    }
}