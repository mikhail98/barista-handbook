package com.eratart.baristashandbook.presentation.itemslist.di

import com.eratart.baristashandbook.presentation.itemslist.viewmodel.ItemsListViewModel
import com.eratart.baristashandbook.presentation.itemslist.view.ItemsListActivity
import org.koin.dsl.module

val itemsListModule = module {
    scope<ItemsListActivity> {
        scoped { ItemsListViewModel(get()) }
    }
}