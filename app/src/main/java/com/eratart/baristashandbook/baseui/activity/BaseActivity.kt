package com.eratart.baristashandbook.baseui.activity

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.baseui.view.relativelayout.DefaultFullscreenLoader
import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.eratart.baristashandbook.core.ext.gone
import com.eratart.baristashandbook.core.ext.observe
import com.eratart.baristashandbook.core.ext.visibleWithAlpha
import com.eratart.baristashandbook.domain.firebase.IFirebaseAnalyticsManager
import com.eratart.baristashandbook.tools.navigator.IGlobalNavigator
import com.eratart.baristashandbook.tools.permissions.IPermissionsManager
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.core.scope.Scope

abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding> : AppCompatActivity(),
    AndroidScopeComponent {

    abstract val binding: VB?
    abstract val viewModel: VM

    abstract fun initView()
    abstract fun initViewModel()

    protected open fun onWritePermissionsGranted() {}

    override val scope: Scope by activityScope()
    protected val globalNavigator: IGlobalNavigator by inject()
    val analyticsManager: IFirebaseAnalyticsManager by inject()

    private val activityRootView by lazy { binding?.root }
    private lateinit var loader: DefaultFullscreenLoader

    protected open val koinModules: List<Module> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(koinModules)
        super.onCreate(savedInstanceState)
        activityRootView?.run {
            setContentView(this)
        }
        initLoader()
        initActivity()

        viewModel.apply {
            observe(isLoading, ::renderLoader)
            observe(message, ::handleMessage)
            onCreate()
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
        if (isVisible) {
            loader.visibleWithAlpha()
        } else {
            loader.gone()
        }
    }

    private fun handleMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        unloadKoinModules(koinModules)
        viewModel.onDestroy()
        super.onDestroy()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            IPermissionsManager.PERMISSION_REQUEST_CODE_STORAGE ->
                if (isAllPermissionsEnabled(grantResults)) {
                    onWritePermissionsGranted()
                }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun isAllPermissionsEnabled(grantResults: IntArray) =
        grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }
}