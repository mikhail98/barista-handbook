package com.eratart.baristashandbook.presentation.mainmenu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.eratart.baristashandbook.domain.interactor.cache.IAppCacheInteractor
import com.eratart.baristashandbook.domain.interactor.tg.INewsInteractor
import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.ItemCategory
import com.eratart.baristashandbook.domain.preferences.IAppPreferences
import com.eratart.baristashandbook.tools.resources.IResourceManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val appCacheInteractor: IAppCacheInteractor,
    resourceManager: IResourceManager,
    appPreferences: IAppPreferences
) : BaseViewModel(resourceManager, appPreferences) {

    private val _newsAmount = MutableLiveData<Int>()
    val newsAmount: LiveData<Int> = _newsAmount

    private val _dishesFromCache = MutableLiveData<List<Dish>>()
    val dishesFromCache: LiveData<List<Dish>> = _dishesFromCache

    private val _itemCategoriesFromCache = MutableLiveData<List<ItemCategory>>()
    val itemCategoriesFromCache: LiveData<List<ItemCategory>> = _itemCategoriesFromCache

    override fun onCreate() {
        _newsAmount.postValue(appCacheInteractor.getNews().size)
        _dishesFromCache.postValue(appCacheInteractor.getDishes())
        _itemCategoriesFromCache.postValue(appCacheInteractor.getItemCategories())
    }
}