package com.eratart.baristashandbook.presentation.itemdetails.view

import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.databinding.ActivityArtInstructionsBinding
import com.eratart.baristashandbook.databinding.ActivityItemDetailsBinding
import com.eratart.baristashandbook.presentation.artinstructions.viewmodel.ArtInstructionsViewModel
import com.eratart.baristashandbook.presentation.itemdetails.viewmodel.ItemDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemDetailsActivity :
    BaseActivity<ItemDetailsViewModel, ActivityItemDetailsBinding>() {

    override val viewModel: ItemDetailsViewModel by viewModel()
    override val binding by lazy { ActivityItemDetailsBinding.inflate(layoutInflater) }

    override fun initView() {
    }

    override fun initViewModel() {
    }
}