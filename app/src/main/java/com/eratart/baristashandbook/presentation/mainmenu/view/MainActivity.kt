package com.eratart.baristashandbook.presentation.mainmenu.view

import com.eratart.baristashandbook.BuildConfig
import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.databinding.ActivityMainMenuBinding
import com.eratart.baristashandbook.presentation.mainmenu.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainMenuBinding>() {

    override val viewModel: MainViewModel by viewModel()
    override val binding by lazy { ActivityMainMenuBinding.inflate(layoutInflater) }

    override fun initView() {
        binding.tv.text = BuildConfig.ARCHIEVE_NAME
    }

    override fun initViewModel() {

    }
}