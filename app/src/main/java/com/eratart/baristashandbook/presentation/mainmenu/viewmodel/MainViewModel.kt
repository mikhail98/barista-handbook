package com.eratart.baristashandbook.presentation.mainmenu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.eratart.baristashandbook.domain.interactor.cache.IAppCacheInteractor
import com.eratart.baristashandbook.domain.interactor.tg.INewsInteractor
import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.preferences.IAppPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val appCacheInteractor: IAppCacheInteractor,
    appPreferences: IAppPreferences
) : BaseViewModel(appPreferences) {

    private val _dishesFromCache = MutableLiveData<List<Dish>>()
    val dishesFromCache: LiveData<List<Dish>> = _dishesFromCache

    override fun onCreate() {
        fetchDishes()
    }

    fun fetchDishes() {
        _dishesFromCache.postValue(appCacheInteractor.getDishes())
    }

}