package com.eratart.baristashandbook.presentation.splash.di

import com.eratart.baristashandbook.presentation.splash.view.SplashActivity
import com.eratart.baristashandbook.presentation.splash.viewmodel.SplashViewModel
import org.koin.dsl.module

val splashModule = module {
    scope<SplashActivity> {
        scoped { SplashViewModel(get(), get(), get()) }
    }
}