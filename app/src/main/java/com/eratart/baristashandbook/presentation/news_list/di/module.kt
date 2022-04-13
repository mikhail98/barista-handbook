package com.eratart.baristashandbook.presentation.news_list.di

import com.eratart.baristashandbook.presentation.news_list.view.NewsListActivity
import com.eratart.baristashandbook.presentation.news_list.viewmodel.NewsListViewModel
import org.koin.dsl.module

val newsListModule = module {
    scope<NewsListActivity> {
        scoped { NewsListViewModel(get(), get(), get()) }
    }
}