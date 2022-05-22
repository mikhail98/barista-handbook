package com.eratart.baristashandbook.presentation.artinstructions.di

import com.eratart.baristashandbook.presentation.artinstructions.viewmodel.ArtInstructionsViewModel
import org.koin.dsl.module

val artInstructionsModule = module {
    single { ArtInstructionsViewModel(get(), get()) }
}