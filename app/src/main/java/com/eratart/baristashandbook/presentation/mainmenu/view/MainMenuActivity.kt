package com.eratart.baristashandbook.presentation.mainmenu.view

import androidx.constraintlayout.widget.ConstraintLayout
import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.core.ext.dpToPx
import com.eratart.baristashandbook.core.ext.getScreenWidth
import com.eratart.baristashandbook.databinding.ActivityMainMenuBinding
import com.eratart.baristashandbook.presentation.mainmenu.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainMenuActivity : BaseActivity<MainViewModel, ActivityMainMenuBinding>() {

    override val viewModel: MainViewModel by viewModel()
    override val binding by lazy { ActivityMainMenuBinding.inflate(layoutInflater) }
    private val llMenu by lazy { binding.llMenu }
    private val itemNews by lazy { binding.itemNews }
    private val itemDishes by lazy { binding.itemDishes }
    private val itemDrinks by lazy { binding.itemDrinks }
    private val itemLatteArt by lazy { binding.itemLatteArt }
    private val itemFavorites by lazy { binding.itemFavorites }
    private val viewGradient by lazy { binding.viewGradient }
    private val btnInfo by lazy { binding.btnInfo }
    private val btnSettings by lazy { binding.btnSettings }

    override fun initView() {
        initMenuLayout()
        initClickListeners()
    }

    private fun initMenuLayout() {
        val newHeight = (getScreenWidth() - resources.getDimension(R.dimen.default_margin)) * 3 / 2
        val llMenuParams = llMenu.layoutParams as ConstraintLayout.LayoutParams
        llMenuParams.height = newHeight.toInt()
        llMenu.layoutParams = llMenuParams

        val viewGradientParams = viewGradient.layoutParams as ConstraintLayout.LayoutParams
        viewGradientParams.height = newHeight.toInt() - 20.dpToPx()
        viewGradient.layoutParams = viewGradientParams
    }

    private fun initClickListeners() {
        itemNews.setOnClickListener {}
        itemDishes.setOnClickListener {}
        itemDrinks.setOnClickListener {
            globalNavigator.startItemsListActivity(this)
        }
        itemLatteArt.setOnClickListener {}
        itemFavorites.setOnClickListener {}
        btnInfo.setOnClickListener { }
        btnSettings.setOnClickListener { }
    }

    override fun initViewModel() {

    }
}