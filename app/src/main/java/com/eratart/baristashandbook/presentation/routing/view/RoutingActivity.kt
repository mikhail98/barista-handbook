package com.eratart.baristashandbook.presentation.routing.view

import android.os.Bundle
import android.view.WindowManager
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.viewbinding.ViewBinding
import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.core.ext.observe
import com.eratart.baristashandbook.core.ext.postDelayed
import com.eratart.baristashandbook.databinding.ActivityRoutingBinding
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.presentation.routing.viewmodel.RoutingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class RoutingActivity : BaseActivity<RoutingViewModel, ViewBinding>() {

    override val viewModel: RoutingViewModel by viewModel()
    override val binding by lazy { ActivityRoutingBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    override fun initView() {
    }

    override fun initViewModel() {
        viewModel.apply {
            observe(showOnboarding, ::handleOnboardingShown)
            loadDataToCache()
        }
    }

    private fun handleOnboardingShown(isShown: Boolean) {
        postDelayed(1500) {
            globalNavigator.startMainMenuActivity(this)
            if (!isShown) {
                globalNavigator.startOnboardingActivity(this)
            }
            overrideAnimationAndClose()
        }
    }

    private fun overrideAnimationAndClose() {
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }
}