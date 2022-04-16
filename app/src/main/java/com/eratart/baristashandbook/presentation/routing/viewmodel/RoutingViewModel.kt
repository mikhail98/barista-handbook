package com.eratart.baristashandbook.presentation.routing.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.eratart.baristashandbook.core.ext.launchFlow
import com.eratart.baristashandbook.core.ext.onNext
import com.eratart.baristashandbook.domain.interactor.cache.IAppCacheInteractor
import com.eratart.baristashandbook.domain.preferences.IAppPreferences
import com.eratart.baristashandbook.domain.preferences.IOnboardingPreferences
import com.eratart.baristashandbook.tools.resources.IResourceManager

class RoutingViewModel(
    private val onboardingPreferences: IOnboardingPreferences,
    private val appCacheInteractor: IAppCacheInteractor,
    resourceManager: IResourceManager,
    appPreferences: IAppPreferences
) : BaseViewModel(resourceManager, appPreferences) {

    private val _data = MutableLiveData<Pair<Boolean, Boolean>>()
    val data: LiveData<Pair<Boolean, Boolean>> = _data

    override fun onCreate() {
        loadDataToCache()
    }

    private fun loadDataToCache() {
        viewModelScope.launchFlow {
            appCacheInteractor.initCache()
                .onNext { data ->
                    _data.postValue(Pair(onboardingPreferences.isStartupOnboardingShown(), data))
                }
        }
    }
}