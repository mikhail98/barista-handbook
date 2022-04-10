package com.eratart.baristashandbook.baseui.fragment

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.core.scope.Scope

abstract class BaseBottomSheetDialogFragment<VM : BaseViewModel, VB : ViewBinding> :
    BottomSheetDialogFragment(), AndroidScopeComponent {

    abstract val viewModel: VM?

    override val scope: Scope by fragmentScope()

    protected var binding: VB? = null
    private val fragmentRootView by lazy { binding?.root }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    protected open fun initFragment() {}

    abstract fun initView()

}