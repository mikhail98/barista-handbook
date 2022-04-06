package com.eratart.baristashandbook.presentation.itemslist.view

import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.presentation.itemslist.viewmodel.ItemsListViewModel
import com.eratart.baristashandbook.presentationbase.itemslistactivity.BaseItemsListActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemsListActivity : BaseItemsListActivity<ItemsListViewModel>() {

    override val titleRes = R.string.main_menu_drinks
    override val viewModel: ItemsListViewModel by viewModel()

    override fun initViewModel() {

    }

    override fun onItemClick(item: Any, pos: Int) {

    }
}