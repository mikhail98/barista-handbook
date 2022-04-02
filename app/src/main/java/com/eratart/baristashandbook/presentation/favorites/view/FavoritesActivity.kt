package com.eratart.baristashandbook.presentation.favorites.view

import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.databinding.ActivityArtInstructionsBinding
import com.eratart.baristashandbook.databinding.ActivityFavoritesBinding
import com.eratart.baristashandbook.presentation.artinstructions.viewmodel.ArtInstructionsViewModel
import com.eratart.baristashandbook.presentation.favorites.viewmodel.FavoritesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesActivity :
    BaseActivity<FavoritesViewModel, ActivityFavoritesBinding>() {

    override val viewModel: FavoritesViewModel by viewModel()
    override val binding by lazy { ActivityFavoritesBinding.inflate(layoutInflater) }

    override fun initView() {
    }

    override fun initViewModel() {
    }
}