package com.eratart.baristashandbook.presentation.itemdetails.di

import com.eratart.baristashandbook.presentation.itemdetails.view.ItemDetailsActivity
import com.eratart.baristashandbook.presentation.itemdetails.viewmodel.ItemDetailsViewModel
import com.eratart.baristashandbook.tools.share.IShareTool
import com.eratart.baristashandbook.tools.share.IShareUtil
import com.eratart.baristashandbook.tools.share.ShareTool
import com.eratart.baristashandbook.tools.share.ShareUtil
import org.koin.dsl.module

val itemDetailsModule = module {
    single { ItemDetailsViewModel(get(), get(), get(), get()) }
    scope<ItemDetailsActivity> {
        scoped<IShareTool> { ShareTool(get()) }
        scoped<IShareUtil> { ShareUtil(get(), get()) }
    }
}