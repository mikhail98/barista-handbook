package com.eratart.baristashandbook.presentation.news_details.di

import com.eratart.baristashandbook.presentation.news_details.view.NewsDetailsActivity
import com.eratart.baristashandbook.presentation.news_details.viewmodel.NewsDetailsViewModel
import com.eratart.baristashandbook.tools.share.IShareTool
import com.eratart.baristashandbook.tools.share.ShareTool
import org.koin.dsl.module

val newsDetailsModule = module {
    single { NewsDetailsViewModel(get(), get()) }
    scope<NewsDetailsActivity> {
        scoped<IShareTool> { ShareTool(get()) }
    }
}