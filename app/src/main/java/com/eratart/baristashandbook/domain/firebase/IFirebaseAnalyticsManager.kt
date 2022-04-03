package com.eratart.baristashandbook.domain.firebase

interface IFirebaseAnalyticsManager {

    /**
     * Send event to firebase analytics
     *
     * @param eventName is tracking event name
     */
    fun logEvent(eventName: String)
}