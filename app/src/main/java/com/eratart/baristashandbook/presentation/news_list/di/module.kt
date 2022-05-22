package com.eratart.baristashandbook.presentation.news_list.di

import com.eratart.baristashandbook.presentation.news_list.view.NewsListActivity
import com.eratart.baristashandbook.presentation.news_list.viewmodel.NewsListViewModel
import com.eratart.baristashandbook.tools.share.IShareTool
import com.eratart.baristashandbook.tools.share.ShareTool
import org.koin.dsl.module

val newsListModule = module {
    single { NewsListViewModel(get(), get(), get()) }
    scope<NewsListActivity> {
        scoped<IShareTool> { ShareTool(get()) }
    }
}