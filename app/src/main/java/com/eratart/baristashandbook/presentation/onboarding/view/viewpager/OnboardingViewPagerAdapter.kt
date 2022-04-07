package com.eratart.baristashandbook.presentation.onboarding.view.viewpager

import androidx.fragment.app.FragmentActivity
import com.eratart.baristashandbook.baseui.view.viewpager.BaseViewPager2Adapter
import com.eratart.baristashandbook.presentation.onboarding.model.OnboardingModel
import com.eratart.baristashandbook.presentation.onboarding.view.fragment.OnboardingFragment

class OnboardingViewPagerAdapter(activity: FragmentActivity, models: List<OnboardingModel>) :
    BaseViewPager2Adapter(activity) {

    override val fragments by lazy {
        models.map { model -> OnboardingFragment.newInstance(model) }
    }
}