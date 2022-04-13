package com.eratart.baristashandbook.presentation.mainmenu.di

import com.eratart.baristashandbook.presentation.mainmenu.view.MainMenuActivity
import com.eratart.baristashandbook.presentation.mainmenu.viewmodel.MainViewModel
import com.eratart.baristashandbook.tools.customtabs.CustomTabTool
import com.eratart.baristashandbook.tools.customtabs.ICustomTabTool
import org.koin.dsl.module

val mainMenuModule = module {
    scope<MainMenuActivity> {
        scoped { MainViewModel(get(), get(), get()) }
        scoped<ICustomTabTool> { CustomTabTool(get()) }
    }
}