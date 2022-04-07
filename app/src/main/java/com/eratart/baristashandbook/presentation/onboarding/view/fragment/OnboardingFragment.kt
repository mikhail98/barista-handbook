package com.eratart.baristashandbook.presentation.onboarding.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eratart.baristashandbook.baseui.fragment.BaseFragment
import com.eratart.baristashandbook.core.ext.lazyArgument
import com.eratart.baristashandbook.core.ext.withArgs
import com.eratart.baristashandbook.databinding.FragmentOnboardingBinding
import com.eratart.baristashandbook.presentation.onboarding.model.OnboardingModel
import com.eratart.baristashandbook.presentation.onboarding.viewmodel.OnboardingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnboardingFragment : BaseFragment<OnboardingViewModel, FragmentOnboardingBinding>() {

    companion object {
        private const val ARGS_ONBOARDING_MODEL = "OnboardingFragment.ARGS_ONBOARDING_MODEL"

        fun newInstance(onboardingModel: OnboardingModel) = OnboardingFragment().withArgs {
            putParcelable(ARGS_ONBOARDING_MODEL, onboardingModel)
        }
    }

    override val viewModel: OnboardingViewModel by viewModel()

    private val onboardingModel by lazyArgument<OnboardingModel>(ARGS_ONBOARDING_MODEL)
    private val ivOnboardingImage by lazy { binding?.ivOnboardingImage }

    override fun initFragment() {
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return with(FragmentOnboardingBinding.inflate(inflater)) {
            binding = this
            root
        }
    }

    override fun initView() {
        onboardingModel?.run {
            ivOnboardingImage?.setImageResource(imageRes)
        }
    }
}