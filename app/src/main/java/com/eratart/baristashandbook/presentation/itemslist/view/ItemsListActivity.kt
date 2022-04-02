package com.eratart.baristashandbook.presentation.itemslist.view

import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.databinding.ActivityArtInstructionsBinding
import com.eratart.baristashandbook.databinding.ActivityItemsListBinding
import com.eratart.baristashandbook.presentation.artinstructions.viewmodel.ArtInstructionsViewModel
import com.eratart.baristashandbook.presentation.itemslist.viewmodel.ItemsListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemsListActivity :
    BaseActivity<ItemsListViewModel, ActivityItemsListBinding>() {

    override val viewModel: ItemsListViewModel by viewModel()
    override val binding by lazy { ActivityItemsListBinding.inflate(layoutInflater) }

    override fun initView() {
    }

    override fun initViewModel() {
    }
}