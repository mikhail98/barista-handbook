package com.eratart.baristashandbook.presentation.news_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.eratart.baristashandbook.domain.interactor.tg.INewsInteractor
import com.eratart.baristashandbook.domain.model.NewsBot
import com.eratart.baristashandbook.domain.preferences.IAppPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewsListViewModel(private val newsInteractor: INewsInteractor, appPreferences: IAppPreferences) :
    BaseViewModel(appPreferences) {

    private val _news = MutableLiveData<List<NewsBot>>()
    val news: LiveData<List<NewsBot>> = _news

    override fun onCreate() {
        fetchNews()
    }

    private fun fetchNews() {
        setLoading(true)
        CoroutineScope(Dispatchers.IO).launch {
            delay(500)
            _news.postValue(newsInteractor.getNews().asReversed())
            setLoading(false)
        }
    }
}