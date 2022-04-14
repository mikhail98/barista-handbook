package com.eratart.baristashandbook.presentation.artinstructions.di

import com.eratart.baristashandbook.presentation.artinstructions.view.ArtInstructionsActivity
import com.eratart.baristashandbook.presentation.artinstructions.viewmodel.ArtInstructionsViewModel
import org.koin.dsl.module

val artInstructionsModule = module {
    scope<ArtInstructionsActivity> {
        scoped { ArtInstructionsViewModel(get(), get()) }
    }
}