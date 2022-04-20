package com.eratart.baristashandbook.presentation.disheslist.di

import com.eratart.baristashandbook.presentation.disheslist.view.DishesListActivity
import com.eratart.baristashandbook.presentation.disheslist.viewmodel.DishesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dishesListModule = module {
    scope<DishesListActivity> {
        viewModel { DishesListViewModel(get(), get()) }
    }
}