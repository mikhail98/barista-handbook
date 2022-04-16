package com.eratart.baristashandbook.presentation.news_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.eratart.baristashandbook.domain.cache.IAppCache
import com.eratart.baristashandbook.domain.interactor.cache.IAppCacheInteractor
import com.eratart.baristashandbook.domain.model.NewsBot
import com.eratart.baristashandbook.domain.preferences.IAppPreferences
import com.eratart.baristashandbook.tools.resources.IResourceManager

class NewsListViewModel(
    private val appCacheInteractor: IAppCacheInteractor,
    resourceManager: IResourceManager,
    appPreferences: IAppPreferences
) :
    BaseViewModel(resourceManager, appPreferences) {

    private val _news = MutableLiveData<List<NewsBot>>()
    val news: LiveData<List<NewsBot>> = _news

    override fun onCreate() {
        fetchNews()
    }

    private fun fetchNews() {
        _news.postValue(appCacheInteractor.getNews())
    }
}