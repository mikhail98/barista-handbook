package com.eratart.baristashandbook.presentation.settings.view

import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.databinding.ActivitySettingsBinding
import com.eratart.baristashandbook.presentation.settings.viewmodel.SettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsActivity :
    BaseActivity<SettingsViewModel, ActivitySettingsBinding>() {

    override val viewModel: SettingsViewModel by viewModel()
    override val binding by lazy { ActivitySettingsBinding.inflate(layoutInflater) }

    override fun initView() {
    }

    override fun initViewModel() {
    }
}