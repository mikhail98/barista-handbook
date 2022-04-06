package com.eratart.baristashandbook.presentation.itemscategorieslist.di

import com.eratart.baristashandbook.presentation.itemscategorieslist.view.ItemsCategoriesListActivity
import com.eratart.baristashandbook.presentation.itemscategorieslist.viewmodel.ItemsCategoriesListViewModel
import org.koin.dsl.module

val itemsListModule = module {
    scope<ItemsCategoriesListActivity> {
        scoped { ItemsCategoriesListViewModel(get()) }
    }
}