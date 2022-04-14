package com.eratart.baristashandbook.presentation.onboarding.viewmodel

import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.eratart.baristashandbook.domain.preferences.IAppPreferences
import com.eratart.baristashandbook.domain.preferences.IOnboardingPreferences
import com.eratart.baristashandbook.tools.resources.IResourceManager

class OnboardingViewModel(
    private val onboardingPreferences: IOnboardingPreferences,
    resourceManager: IResourceManager,
    appPreferences: IAppPreferences
) : BaseViewModel(resourceManager, appPreferences) {

    fun saveOnboardingShown() {
        onboardingPreferences.saveStartupOnboardingShown(true)
    }
}