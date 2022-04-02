package com.eratart.baristashandbook.domain.preferences

interface IOnboardingPreferences {

    companion object {
        const val ONBOARDING_SHOWN = "IOnboardingPreferences.ONBOARDING_SHOWN"
    }

    fun isOnboardingShown(): Boolean
    fun saveOnboardingShown(isShown: Boolean)

}