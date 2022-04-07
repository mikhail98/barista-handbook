package com.eratart.baristashandbook.presentation.onboarding.di

import com.eratart.baristashandbook.presentation.onboarding.view.OnboardingActivity
import com.eratart.baristashandbook.presentation.onboarding.viewmodel.OnboardingViewModel
import org.koin.dsl.module

val onboardingModule = module {
    scope<OnboardingActivity> {
        scoped { OnboardingViewModel(get(), get()) }
    }
}