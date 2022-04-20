package com.eratart.baristashandbook.presentation.news_details.di

import com.eratart.baristashandbook.presentation.news_details.view.NewsDetailsActivity
import com.eratart.baristashandbook.presentation.news_details.viewmodel.NewsDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsDetailsModule = module {
    scope<NewsDetailsActivity> {
        viewModel { NewsDetailsViewModel(get(), get()) }
    }
}