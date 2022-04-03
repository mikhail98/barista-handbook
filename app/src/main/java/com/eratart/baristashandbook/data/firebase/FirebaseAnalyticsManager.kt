package com.eratart.baristashandbook.data.firebase

import android.os.Bundle
import com.eratart.baristashandbook.BuildConfig
import com.eratart.baristashandbook.domain.firebase.IFirebaseAnalyticsManager
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseAnalyticsManager(private val firebaseAnalytics: FirebaseAnalytics) :
    IFirebaseAnalyticsManager {

    companion object {
        private const val PARAM_VERSION = "appVersion"
        private const val PARAM_PLATFORM = "platform"
        private const val PLATFORM = "Android"
    }

    override fun logEvent(eventName: String) {
        if (!BuildConfig.DEBUG) {
            val bundle = Bundle().apply {
                putString(PARAM_VERSION, BuildConfig.VERSION_NAME)
                putString(PARAM_PLATFORM, PLATFORM)
            }
            firebaseAnalytics.logEvent(eventName, bundle)
        }
    }
}