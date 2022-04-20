package com.eratart.baristashandbook.presentation.appinfo.di

import com.eratart.baristashandbook.presentation.appinfo.view.AppInfoActivity
import com.eratart.baristashandbook.presentation.appinfo.viewmodel.AppInfoViewModel
import com.eratart.baristashandbook.tools.share.IShareTool
import com.eratart.baristashandbook.tools.share.ShareTool
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appInfoModule = module {
    scope<AppInfoActivity> {
        viewModel { AppInfoViewModel(get(), get()) }
        scoped<IShareTool> { ShareTool(get()) }
    }
}