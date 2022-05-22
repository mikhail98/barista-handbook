package com.eratart.baristashandbook.presentation.onboarding.di

import com.eratart.baristashandbook.presentation.onboarding.viewmodel.OnboardingViewModel
import org.koin.dsl.module

val onboardingModule = module {
    single { OnboardingViewModel(get(), get(), get()) }
}