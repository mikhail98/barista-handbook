package com.eratart.baristashandbook.presentation.onboarding.model

import com.eratart.baristashandbook.R

object OnboardingModelsUtil {

    private val model1 by lazy {
        OnboardingModel(
            R.string.onboarding_title_1,
            R.string.onboarding_description_1,
            R.drawable.ic_placeholder,
            isSkippable = true,
            isSwipable = true,
            null
        )
    }

    private val model2 by lazy {
        OnboardingModel(
            R.string.onboarding_title_2,
            R.string.onboarding_description_2,
            R.drawable.ic_placeholder,
            isSkippable = true,
            isSwipable = true,
            null
        )
    }

    private val model3 by lazy {
        OnboardingModel(
            R.string.onboarding_title_3,
            R.string.onboarding_description_3,
            R.drawable.ic_placeholder,
            isSkippable = true,
            isSwipable = true,
            null
        )
    }

    private val model4 by lazy {
        OnboardingModel(
            R.string.onboarding_title_4,
            R.string.onboarding_description_4,
            R.drawable.ic_placeholder,
            isSkippable = true,
            isSwipable = true,
            null
        )
    }

    private val model5 by lazy {
        OnboardingModel(
            R.string.onboarding_title_5,
            R.string.onboarding_description_5,
            R.drawable.ic_placeholder,
            isSkippable = true,
            isSwipable = true,
            null
        )
    }

    fun getModelsList() = listOf(model1, model2, model3, model4, model5)

}