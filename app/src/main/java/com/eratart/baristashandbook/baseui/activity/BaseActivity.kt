package com.eratart.baristashandbook.baseui.activity

import android.os.Bundle
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.baseui.view.DefaultFullscreenLoader
import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.eratart.baristashandbook.core.ext.gone
import com.eratart.baristashandbook.core.ext.observe
import com.eratart.baristashandbook.core.ext.setVisibleAlpha
import com.eratart.baristashandbook.tools.navigator.IGlobalNavigator
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.core.scope.Scope

abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding> : AppCompatActivity(),
    AndroidScopeComponent {

    abstract val binding: VB?
    abstract val viewModel: VM

    abstract fun initView()
    abstract fun initViewModel()

    override val scope: Scope by activityScope()
    protected val globalNavigator: IGlobalNavigator by inject()

    private val activityRootView by lazy { binding?.root }
    private lateinit var loader: DefaultFullscreenLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRootView?.run {
            setContentView(this)
        }
        initLoader()
        initActivity()

        viewModel.apply {
            observe(isLoading, ::renderLoader)
        }

        supportActionBar?.hide()
        changeStatusBarColor()
    }

    private fun initLoader() {
        loader = DefaultFullscreenLoader(this)
        with(loader) {
            if (activityRootView is ViewGroup) {
                (activityRootView as ViewGroup).addView(this)
            }
            gone()
            bringToFront()
        }
    }

    @CallSuper
    protected open fun initActivity() {
        initView()
        initViewModel()
    }

    protected fun changeStatusBarColor(color: Int = R.color.action_bar) {
        window.statusBarColor = ContextCompat.getColor(this, color)
    }

    protected fun renderLoader(isVisible: Boolean) {
        loader.setVisibleAlpha(isVisible)
    }
}