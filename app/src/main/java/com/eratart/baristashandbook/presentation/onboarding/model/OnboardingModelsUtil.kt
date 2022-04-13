package com.eratart.baristashandbook.presentation.onboarding.model

import com.eratart.baristashandbook.R

object OnboardingModelsUtil {

    private val model1 by lazy {
        OnboardingModel(
            R.string.onboarding_title_1,
            R.drawable.onboarding_1,
            isSkippable = true,
            isSwipable = true,
            null
        )
    }

    private val model2 by lazy {
        OnboardingModel(
            R.string.onboarding_title_2,
            R.drawable.onboarding_2,
            isSkippable = true,
            isSwipable = true,
            null
        )
    }

    private val model3 by lazy {
        OnboardingModel(
            R.string.onboarding_title_3,
            R.drawable.onboarding_3,
            isSkippable = true,
            isSwipable = true,
            null
        )
    }

    fun getModelsList() = listOf(model1, model2, model3)

}