package com.eratart.baristashandbook.presentation.dishdetails.di

import com.eratart.baristashandbook.presentation.dishdetails.view.DishDetailsActivity
import com.eratart.baristashandbook.presentation.dishdetails.viewmodel.DishDetailsViewModel
import com.eratart.baristashandbook.tools.share.IShareTool
import com.eratart.baristashandbook.tools.share.IShareUtil
import com.eratart.baristashandbook.tools.share.ShareTool
import com.eratart.baristashandbook.tools.share.ShareUtil
import org.koin.dsl.module

val dishDetailsModule = module {
    single { DishDetailsViewModel(get(), get(), get()) }
    scope<DishDetailsActivity> {
        scoped<IShareTool> { ShareTool(get()) }
        scoped<IShareUtil> { ShareUtil(get(), get()) }
    }
}