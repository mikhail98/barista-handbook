package com.eratart.baristashandbook.presentation.dishdetails.di

import com.eratart.baristashandbook.presentation.dishdetails.view.DishDetailsActivity
import com.eratart.baristashandbook.presentation.dishdetails.viewmodel.DishDetailsViewModel
import com.eratart.baristashandbook.tools.share.IShareTool
import com.eratart.baristashandbook.tools.share.IShareUtil
import com.eratart.baristashandbook.tools.share.ShareTool
import com.eratart.baristashandbook.tools.share.ShareUtil
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dishDetailsModule = module {
    scope<DishDetailsActivity> {
        viewModel { DishDetailsViewModel(get(), get(), get()) }
        scoped<IShareTool> { ShareTool(get()) }
        scoped<IShareUtil> { ShareUtil(get(), get()) }
    }
}