package com.eratart.baristashandbook.presentation.onboarding.view

import android.view.animation.AccelerateDecelerateInterpolator
import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.baseui.view.viewpager.PageChangedListener2
import com.eratart.baristashandbook.baseui.view.viewpager.ViewPageScroller
import com.eratart.baristashandbook.core.constants.IntConstants
import com.eratart.baristashandbook.core.ext.setTextAnimation
import com.eratart.baristashandbook.core.ext.setViewPageScroller
import com.eratart.baristashandbook.databinding.ActivityOnboardingBinding
import com.eratart.baristashandbook.presentation.onboarding.model.OnboardingModel
import com.eratart.baristashandbook.presentation.onboarding.model.OnboardingModelsUtil
import com.eratart.baristashandbook.presentation.onboarding.view.viewpager.OnboardingViewPagerAdapter
import com.eratart.baristashandbook.presentation.onboarding.viewmodel.OnboardingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnboardingActivity : BaseActivity<OnboardingViewModel, ActivityOnboardingBinding>() {

    override val viewModel: OnboardingViewModel by viewModel()
    override val binding by lazy { ActivityOnboardingBinding.inflate(layoutInflater) }

    private val models by lazy { OnboardingModelsUtil.getModelsList() }

    private val vpOnboarding by lazy { binding.vpOnboarding }
    private val tvTitle by lazy { binding.tvTitle }
    private val tvSubtitle by lazy { binding.tvSubtitle }
    private val btnNext by lazy { binding.btnNext }
    private val tlDots by lazy { binding.tlDots }

    private val pagerAdapter by lazy { OnboardingViewPagerAdapter(this, models) }

    override fun initView() {
        vpOnboarding.apply {
            offscreenPageLimit = pagerAdapter.itemCount
            adapter = pagerAdapter
            tlDots.setViewPager2(this)
            setViewPageScroller(ViewPageScroller(context, AccelerateDecelerateInterpolator()))
            registerOnPageChangeCallback(PageChangedListener2 {
                handleModelSelected(models[it], true)
            })
            selectItem(IntConstants.ZERO, false)
        }
        btnNext.setOnClickListener {
            val currentItem = vpOnboarding.currentItem
            if (currentItem == models.size - IntConstants.ONE) {
                skipOnboarding()
            } else {
                selectItem(currentItem + IntConstants.ONE, true)
            }
        }
    }

    private fun selectItem(pos: Int, animateTextChanges: Boolean) {
        vpOnboarding.setCurrentItem(pos, true)
        handleModelSelected(models[pos], animateTextChanges)
    }

    private fun skipOnboarding() {
        viewModel.saveOnboardingShown()
        finish()
    }

    private fun handleModelSelected(model: OnboardingModel, animateTextChanges: Boolean) {
        vpOnboarding.isUserInputEnabled = model.isSwipable
        if (animateTextChanges) {
            tvTitle.setTextAnimation(model.title)
            tvSubtitle.setTextAnimation(model.subtitle)
        } else {
            tvTitle.setText(model.title)
            tvSubtitle.setText(model.subtitle)
        }
    }

    override fun initViewModel() {
    }
}