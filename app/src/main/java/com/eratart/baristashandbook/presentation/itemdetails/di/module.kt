package com.eratart.baristashandbook.presentation.itemdetails.di

import com.eratart.baristashandbook.presentation.itemdetails.view.ItemDetailsActivity
import com.eratart.baristashandbook.presentation.itemdetails.viewmodel.ItemDetailsViewModel
import org.koin.dsl.module

val itemDetailsModule = module {
    scope<ItemDetailsActivity> {
        scoped { ItemDetailsViewModel(get(), get(), get()) }
    }
}