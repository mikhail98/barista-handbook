package com.eratart.baristashandbook.presentation.routing.di

import com.eratart.baristashandbook.presentation.routing.viewmodel.RoutingViewModel
import org.koin.dsl.module

val routingModule = module {
    single { RoutingViewModel(get(), get(), get(), get()) }
}