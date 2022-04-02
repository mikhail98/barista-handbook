package com.eratart.baristashandbook.presentation.onboarding.view

import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.databinding.ActivityOnboardingBinding
import com.eratart.baristashandbook.presentation.onboarding.viewmodel.OnboardingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnboardingActivity :
    BaseActivity<OnboardingViewModel, ActivityOnboardingBinding>() {

    override val viewModel: OnboardingViewModel by viewModel()
    override val binding by lazy { ActivityOnboardingBinding.inflate(layoutInflater) }

    override fun initView() {
    }

    override fun initViewModel() {
    }
}