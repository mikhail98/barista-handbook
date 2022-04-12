package com.eratart.baristashandbook.presentation.dishdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.eratart.baristashandbook.domain.interactor.cache.IAppCacheInteractor
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.preferences.IAppPreferences

class DishDetailsViewModel(
    private val appCacheInteractor: IAppCacheInteractor,
    appPreferences: IAppPreferences
) : BaseViewModel(appPreferences) {

    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> = _items

    override fun onCreate() {
        fetchItems()
    }

    private fun fetchItems() {
        _items.postValue(appCacheInteractor.getItems())
    }

}