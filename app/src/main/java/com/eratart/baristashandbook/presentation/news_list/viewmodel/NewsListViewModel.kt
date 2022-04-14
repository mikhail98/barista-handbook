package com.eratart.baristashandbook.presentation.news_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.eratart.baristashandbook.core.ext.launchFlow
import com.eratart.baristashandbook.core.ext.onNext
import com.eratart.baristashandbook.domain.interactor.tg.INewsInteractor
import com.eratart.baristashandbook.domain.model.NewsBot
import com.eratart.baristashandbook.domain.preferences.IAppPreferences
import com.eratart.baristashandbook.tools.resources.IResourceManager
import kotlinx.coroutines.delay

class NewsListViewModel(
    private val newsInteractor: INewsInteractor,
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
        setLoading(true)
        viewModelScope.launchFlow {
            delay(500)
            newsInteractor.getNews()
                .applyLoader()
                .onNext { data -> handleData(data.asReversed()) }
                .onError { handleData(emptyList()) }
        }
    }

    private fun handleData(news: List<NewsBot>) {
        _news.postValue(news)
    }
}