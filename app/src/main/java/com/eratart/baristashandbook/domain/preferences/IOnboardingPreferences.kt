package com.eratart.baristashandbook.domain.preferences

interface IOnboardingPreferences {

    companion object {
        const val STARTUP_ONBOARDING_SHOWN = "IOnboardingPreferences.STARTUP_ONBOARDING_SHOWN"
    }

    fun isStartupOnboardingShown(): Boolean
    fun saveStartupOnboardingShown(isShown: Boolean)

}