package com.eratart.baristashandbook.presentation.settings.di

import com.eratart.baristashandbook.presentation.settings.view.SettingsActivity
import com.eratart.baristashandbook.presentation.settings.viewmodel.SettingsViewModel
import org.koin.dsl.module

val settingsModule = module {
    scope<SettingsActivity> {
        scoped { SettingsViewModel(get()) }
    }
}