package com.eratart.baristashandbook.presentation.routing.di

import com.eratart.baristashandbook.presentation.routing.view.RoutingActivity
import com.eratart.baristashandbook.presentation.routing.viewmodel.RoutingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val routingModule = module {
    scope<RoutingActivity> {
        viewModel { RoutingViewModel(get(), get(), get(), get()) }
    }
}