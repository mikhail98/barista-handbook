package com.eratart.baristashandbook.data.preferencnes.impl

import android.content.SharedPreferences
import com.eratart.baristashandbook.data.preferencnes.BasePreferences
import com.eratart.baristashandbook.domain.preferences.IOnboardingPreferences

class OnboardingPreferences(sharedPreferences: SharedPreferences) :
    BasePreferences(sharedPreferences), IOnboardingPreferences {

    override fun isOnboardingShown(): Boolean {
        return getObject(IOnboardingPreferences.ONBOARDING_SHOWN, true, Boolean::class.java)
    }

    override fun saveOnboardingShown(isShown: Boolean) {
        saveObject(IOnboardingPreferences.ONBOARDING_SHOWN, isShown)
    }
}