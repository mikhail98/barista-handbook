package com.eratart.baristashandbook.presentation.artinstructions.view

import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.databinding.ActivityArtInstructionsBinding
import com.eratart.baristashandbook.presentation.artinstructions.di.artInstructionsModule
import com.eratart.baristashandbook.presentation.artinstructions.viewmodel.ArtInstructionsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArtInstructionsActivity :
    BaseActivity<ArtInstructionsViewModel, ActivityArtInstructionsBinding>() {

    override val viewModel: ArtInstructionsViewModel by viewModel()
    override val binding by lazy { ActivityArtInstructionsBinding.inflate(layoutInflater) }
    override val koinModules = listOf(artInstructionsModule)

    override fun initView() {
    }

    override fun initViewModel() {
    }
}