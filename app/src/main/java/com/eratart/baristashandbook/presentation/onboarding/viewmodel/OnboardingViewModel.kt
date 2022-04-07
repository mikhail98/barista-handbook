package com.eratart.baristashandbook.presentation.onboarding.viewmodel

import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.eratart.baristashandbook.domain.preferences.IAppPreferences
import com.eratart.baristashandbook.domain.preferences.IOnboardingPreferences

class OnboardingViewModel(
    private val onboardingPreferences: IOnboardingPreferences,
    appPreferences: IAppPreferences
) : BaseViewModel(appPreferences) {

    fun saveOnboardingShown() {
        onboardingPreferences.saveStartupOnboardingShown(true)
    }
}