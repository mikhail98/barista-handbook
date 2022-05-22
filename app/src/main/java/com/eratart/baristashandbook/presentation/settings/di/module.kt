package com.eratart.baristashandbook.presentation.settings.di

import com.eratart.baristashandbook.presentation.settings.viewmodel.SettingsViewModel
import org.koin.dsl.module

val settingsModule = module {
    single { SettingsViewModel(get(), get()) }
}