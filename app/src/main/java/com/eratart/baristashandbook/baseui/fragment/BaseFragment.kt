package com.eratart.baristashandbook.baseui.fragment

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.eratart.baristashandbook.baseui.view.relativelayout.DefaultFullscreenLoader
import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.eratart.baristashandbook.core.ext.gone
import com.eratart.baristashandbook.core.ext.observe
import com.eratart.baristashandbook.core.ext.setVisibleAlpha
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.core.scope.Scope

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding> : Fragment(),
    AndroidScopeComponent {

    protected var binding: VB? = null
    abstract val viewModel: VM

    private val fragmentRootView by lazy { binding?.root }
    private lateinit var loader: DefaultFullscreenLoader

    override val scope: Scope by fragmentScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLoader()

        viewModel.apply {
            observe(isLoading, ::renderLoader)
            onCreate()
        }
        initView()
    }

    abstract fun initFragment()

    abstract fun initView()

    protected open fun renderLoader(isVisible: Boolean) {
        loader.setVisibleAlpha(isVisible)
    }

    private fun initLoader() {
        fragmentRootView?.run {
            loader = DefaultFullscreenLoader(context)
            with(loader) {
                if (fragmentRootView is ViewGroup) {
                    (fragmentRootView as ViewGroup).addView(this)
                }
                this.gone()
                this.bringToFront()
            }
        }
    }
}