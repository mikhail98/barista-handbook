package com.eratart.baristashandbook.presentation.appinfo.di

import com.eratart.baristashandbook.presentation.appinfo.view.AppInfoActivity
import com.eratart.baristashandbook.presentation.appinfo.viewmodel.AppInfoViewModel
import com.eratart.baristashandbook.tools.share.IShareTool
import com.eratart.baristashandbook.tools.share.ShareTool
import org.koin.dsl.module

val appInfoModule = module {
    single { AppInfoViewModel(get(), get()) }
    scope<AppInfoActivity> {
        scoped<IShareTool> { ShareTool(get()) }
    }
}