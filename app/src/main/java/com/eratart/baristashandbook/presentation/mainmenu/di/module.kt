package com.eratart.baristashandbook.presentation.mainmenu.di

import com.eratart.baristashandbook.presentation.mainmenu.view.MainMenuActivity
import com.eratart.baristashandbook.presentation.mainmenu.viewmodel.MainViewModel
import org.koin.dsl.module

val mainMenuModule = module {
    scope<MainMenuActivity> {
        scoped { MainViewModel(get()) }
    }
}