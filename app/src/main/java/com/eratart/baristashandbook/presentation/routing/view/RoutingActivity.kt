package com.eratart.baristashandbook.presentation.routing.view

import android.os.Bundle
import android.view.WindowManager
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.core.ext.observe
import com.eratart.baristashandbook.core.ext.postDelayed
import com.eratart.baristashandbook.databinding.ActivityRoutingBinding
import com.eratart.baristashandbook.presentation.routing.di.routingModule
import com.eratart.baristashandbook.presentation.routing.viewmodel.RoutingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoutingActivity : BaseActivity<RoutingViewModel, ActivityRoutingBinding>() {

    override val viewModel: RoutingViewModel by viewModel()
    override val binding by lazy { ActivityRoutingBinding.inflate(layoutInflater) }

    override val koinModules = listOf(routingModule)

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
            observe(data, ::handleData)
        }
    }

    private fun handleData(data: Pair<Boolean, Boolean>) {
        if (data.second) {
            postDelayed(1500) {
                globalNavigator.startMainMenuActivity(this)
                if (!data.first) {
                    globalNavigator.startOnboardingActivity(this)
                }
                overrideAnimationAndClose()
            }
        }
    }

    private fun overrideAnimationAndClose() {
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }
}