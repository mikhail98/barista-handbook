package com.eratart.baristashandbook.presentation.routing.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
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

    private val _showOnboarding = MutableLiveData<Boolean>()
    val showOnboarding: LiveData<Boolean> = _showOnboarding

    override fun onCreate() {
        loadDataToCache()
    }

    private fun loadDataToCache() {
        appCacheInteractor.initCache()
        _showOnboarding.postValue(onboardingPreferences.isStartupOnboardingShown())
    }
}