package com.eratart.baristashandbook.presentation.disheslist.view

import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.databinding.ActivityArtInstructionsBinding
import com.eratart.baristashandbook.databinding.ActivityDishesListBinding
import com.eratart.baristashandbook.presentation.artinstructions.viewmodel.ArtInstructionsViewModel
import com.eratart.baristashandbook.presentation.disheslist.viewmodel.DishesListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DishesListActivity :
    BaseActivity<DishesListViewModel, ActivityDishesListBinding>() {

    override val viewModel: DishesListViewModel by viewModel()
    override val binding by lazy { ActivityDishesListBinding.inflate(layoutInflater) }

    override fun initView() {
    }

    override fun initViewModel() {
    }
}