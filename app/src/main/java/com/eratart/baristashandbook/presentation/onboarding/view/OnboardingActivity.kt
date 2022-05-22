package com.eratart.baristashandbook.presentation.onboarding.view

import android.view.animation.AccelerateDecelerateInterpolator
import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.baseui.view.viewpager.PageChangedListener2
import com.eratart.baristashandbook.baseui.view.viewpager.ViewPageScroller
import com.eratart.baristashandbook.core.constants.IntConstants
import com.eratart.baristashandbook.core.ext.getScreenWidth
import com.eratart.baristashandbook.core.ext.setHeight
import com.eratart.baristashandbook.core.ext.setTextAnimation
import com.eratart.baristashandbook.core.ext.setViewPageScroller
import com.eratart.baristashandbook.databinding.ActivityOnboardingBinding
import com.eratart.baristashandbook.domain.firebase.AnalyticsEvents
import com.eratart.baristashandbook.presentation.onboarding.di.onboardingModule
import com.eratart.baristashandbook.presentation.onboarding.model.AnalyticsData
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
    private val btnNext by lazy { binding.btnNext }
    private val tlDots by lazy { binding.tlDots }

    private val pagerAdapter by lazy { OnboardingViewPagerAdapter(this, models) }

    private var isAfterClick = false

    override val koinModules = listOf(onboardingModule)

    override fun initView() {
        vpOnboarding.apply {
            setHeight(getScreenWidth() * 10 / 9)
            offscreenPageLimit = pagerAdapter.itemCount
            adapter = pagerAdapter
            tlDots.setViewPager2(this)
            setViewPageScroller(ViewPageScroller(context, AccelerateDecelerateInterpolator()))
            selectItem(IntConstants.ZERO, false, AnalyticsData.NONE)
            registerOnPageChangeCallback(PageChangedListener2 {
                handleModelSelected(models[it], true, AnalyticsData.SWIPE)
            })
        }
        btnNext.setOnClickListener {
            val currentItem = vpOnboarding.currentItem
            if (currentItem == models.size - IntConstants.ONE) {
                skipOnboarding()
            } else {
                selectItem(currentItem + IntConstants.ONE, true, AnalyticsData.CLICK)
            }
        }
    }

    private fun selectItem(pos: Int, animateTextChanges: Boolean, analyticsData: AnalyticsData) {
        handleModelSelected(models[pos], animateTextChanges, analyticsData)
        isAfterClick = true
        vpOnboarding.setCurrentItem(pos, true)
    }

    private fun skipOnboarding() {
        viewModel.saveOnboardingShown()
        finish()
    }

    private fun handleModelSelected(
        model: OnboardingModel, animateTextChanges: Boolean, analyticsData: AnalyticsData
    ) {
        vpOnboarding.isUserInputEnabled = model.isSwipable
        if (animateTextChanges) {
            tvTitle.setTextAnimation(model.title)
        } else {
            tvTitle.setText(model.title)
        }
        val currentItem = vpOnboarding.currentItem
        if (currentItem <= models.size - IntConstants.ONE) {
            when (analyticsData) {
                AnalyticsData.CLICK -> {
                    analyticsManager.logEvent(AnalyticsEvents.action_onboarding_click)
                }
                AnalyticsData.SWIPE -> {
                    if (!isAfterClick) {
                        analyticsManager.logEvent(AnalyticsEvents.action_onboarding_swipe)
                    } else {
                        isAfterClick = false
                    }
                }
            }
        }
    }

    override fun initViewModel() {
    }
}