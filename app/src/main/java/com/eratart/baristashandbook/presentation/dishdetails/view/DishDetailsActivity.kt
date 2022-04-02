package com.eratart.baristashandbook.presentation.dishdetails.view

import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.databinding.ActivityDishDetailsBinding
import com.eratart.baristashandbook.presentation.dishdetails.viewmodel.DishDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DishDetailsActivity :
    BaseActivity<DishDetailsViewModel, ActivityDishDetailsBinding>() {

    override val viewModel: DishDetailsViewModel by viewModel()
    override val binding by lazy { ActivityDishDetailsBinding.inflate(layoutInflater) }

    override fun initView() {
    }

    override fun initViewModel() {
    }
}