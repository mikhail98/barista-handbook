package com.eratart.baristashandbook.data.preferencnes.impl

import android.content.SharedPreferences
import com.eratart.baristashandbook.data.preferencnes.BasePreferences
import com.eratart.baristashandbook.domain.preferences.IOnboardingPreferences

class OnboardingPreferences(sharedPreferences: SharedPreferences) :
    BasePreferences(sharedPreferences), IOnboardingPreferences {

    override fun isStartupOnboardingShown(): Boolean {
        return getObject(IOnboardingPreferences.STARTUP_ONBOARDING_SHOWN, false, Boolean::class.java)
    }

    override fun saveStartupOnboardingShown(isShown: Boolean) {
        saveObject(IOnboardingPreferences.STARTUP_ONBOARDING_SHOWN, isShown)
    }
}