package com.eratart.baristashandbook.presentation.dishdetails.di

import com.eratart.baristashandbook.presentation.dishdetails.view.DishDetailsActivity
import com.eratart.baristashandbook.presentation.dishdetails.viewmodel.DishDetailsViewModel
import org.koin.dsl.module

val dishDetailsModule = module {
    scope<DishDetailsActivity> {
        scoped { DishDetailsViewModel(get(), get()) }
    }
}