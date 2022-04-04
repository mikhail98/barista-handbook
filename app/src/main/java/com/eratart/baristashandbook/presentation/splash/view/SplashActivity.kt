package com.eratart.baristashandbook.presentation.splash.view

import android.annotation.SuppressLint
import android.view.View
import androidx.viewbinding.ViewBinding
import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.core.ext.observe
import com.eratart.baristashandbook.core.ext.postDelayed
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.presentation.splash.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<SplashViewModel, ViewBinding>() {
    override val binding: ViewBinding? = null
    override val viewModel: SplashViewModel by viewModel()

    override fun initView() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }

    override fun initViewModel() {
        viewModel.apply {
            observe(showOnboarding, ::handleOnboardingShown)
            observe(data, ::handleData)
            loadDataToCache()
        }
    }

    //REMOVE
    private fun handleData(data: List<Item>) {
        println("RE:: ${data.first().photos}")
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