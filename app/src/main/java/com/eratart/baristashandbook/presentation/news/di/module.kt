package com.eratart.baristashandbook.presentation.news.di

import com.eratart.baristashandbook.presentation.news.view.NewsActivity
import com.eratart.baristashandbook.presentation.news.viewmodel.NewsViewModel
import org.koin.dsl.module

val newsModule = module {
    scope<NewsActivity> {
        scoped { NewsViewModel(get()) }
    }
}