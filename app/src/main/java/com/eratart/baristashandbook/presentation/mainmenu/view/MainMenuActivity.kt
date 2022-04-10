package com.eratart.baristashandbook.presentation.mainmenu.view

import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.core.ext.dpToPx
import com.eratart.baristashandbook.core.ext.getScreenWidth
import com.eratart.baristashandbook.core.ext.observe
import com.eratart.baristashandbook.core.ext.setHeight
import com.eratart.baristashandbook.core.mock.ItemCategoriesMock
import com.eratart.baristashandbook.databinding.ActivityMainMenuBinding
import com.eratart.baristashandbook.domain.model.Dish
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

    private val dishes by lazy { mutableListOf<Dish>() }

    override fun initView() {
        initMenuLayout()
        initClickListeners()
    }

    private fun initMenuLayout() {
        val newHeight = (getScreenWidth() - resources.getDimension(R.dimen.default_margin)) * 3 / 2
        llMenu.setHeight(newHeight)
        viewGradient.setHeight(newHeight - 20.dpToPx())
    }

    private fun initClickListeners() {
        itemNews.setOnClickListener {}
        itemDishes.setOnClickListener {
            globalNavigator.startDishesListActivity(this, dishes)
        }
        itemDrinks.setOnClickListener {
            globalNavigator.startItemsCategoriesListActivity(
                this, ItemCategoriesMock.getCategories()
            )
        }
        itemLatteArt.setOnClickListener {}
        itemFavorites.setOnClickListener {
            globalNavigator.startFavoritesActivity(this)
        }
        btnInfo.setOnClickListener { }
        btnSettings.setOnClickListener { }
    }

    override fun initViewModel() {
        viewModel.apply {
            observe(dishesFromCache, ::handleDishesFromCache)
            fetchDishes()
        }
    }

    private fun handleDishesFromCache(dishes: List<Dish>) {
        this.dishes.clear()
        this.dishes.addAll(dishes)
    }
}