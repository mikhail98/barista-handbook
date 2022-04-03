package com.eratart.baristashandbook.app

import android.app.Application
import com.eratart.baristashandbook.app.di.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class //BhApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BhApp)
            modules(AppModules.getModules())
        }
    }
}