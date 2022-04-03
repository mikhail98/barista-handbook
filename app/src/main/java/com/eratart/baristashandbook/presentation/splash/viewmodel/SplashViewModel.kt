package com.eratart.baristashandbook.presentation.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.eratart.baristashandbook.domain.interactor.cache.IAppCacheInteractor
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.preferences.IAppPreferences
import com.eratart.baristashandbook.domain.preferences.IOnboardingPreferences

class SplashViewModel(
    private val onboardingPreferences: IOnboardingPreferences,
    private val appCacheInteractor: IAppCacheInteractor,
    appPreferences: IAppPreferences
) : BaseViewModel(appPreferences) {

    //REMOVE
    private val _data = MutableLiveData<List<Item>>()
    val data: LiveData<List<Item>> = _data

    private val _showOnboarding = MutableLiveData<Boolean>()
    val showOnboarding: LiveData<Boolean> = _showOnboarding

    fun loadDataToCache() {
        appCacheInteractor.initCache()
        _data.value = appCacheInteractor.getItems()
        _showOnboarding.postValue(onboardingPreferences.isOnboardingShown())
    }
}