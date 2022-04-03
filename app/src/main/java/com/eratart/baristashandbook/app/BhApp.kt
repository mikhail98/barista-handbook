package com.eratart.baristashandbook.app

import android.app.Application
import com.eratart.baristashandbook.BuildConfig
import com.eratart.baristashandbook.app.di.AppModules
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BhApp : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)
        startKoin {
            androidContext(this@BhApp)
            modules(AppModules.getModules())
        }
    }
}