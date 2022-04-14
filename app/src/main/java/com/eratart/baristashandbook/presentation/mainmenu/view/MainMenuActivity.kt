package com.eratart.baristashandbook.presentation.mainmenu.view

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.baseui.utils.AlertUtil
import com.eratart.baristashandbook.core.ext.*
import com.eratart.baristashandbook.databinding.ActivityMainMenuBinding
import com.eratart.baristashandbook.domain.firebase.AnalyticsEvents
import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.ItemCategory
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
    private val itemCategories by lazy { mutableListOf<ItemCategory>() }

    override fun initView() {
        initPaddings()
        initMenuLayout()
        initClickListeners()
    }

    private fun initPaddings() {
        applyPaddings(btnInfo)
        applyPaddings(btnSettings)
    }

    private fun applyPaddings(view: View) {
        (view.layoutParams as ConstraintLayout.LayoutParams).apply {
            setMargins(leftMargin, topMargin + getStatusBarHeight(), rightMargin, bottomMargin)
        }
    }

    private fun initMenuLayout() {
        val newHeight = (getScreenWidth() - resources.getDimension(R.dimen.default_margin)) * 3 / 2
        llMenu.setHeight(newHeight)
        viewGradient.setHeight(newHeight - 20.dpToPx())
    }

    private fun initClickListeners() {
        itemNews.setOnClickListener {
            analyticsManager.logEvent(AnalyticsEvents.click_main_menu_news)
            globalNavigator.startNewsListActivity(this)
        }
        itemDishes.setOnClickListener {
            analyticsManager.logEvent(AnalyticsEvents.click_main_menu_dishes)
            globalNavigator.startDishesListActivity(this, dishes)
        }
        itemDrinks.setOnClickListener {
            analyticsManager.logEvent(AnalyticsEvents.click_main_menu_drinks)
            globalNavigator.startItemsCategoriesListActivity(this, itemCategories)
        }
        itemLatteArt.setOnClickListener {
            analyticsManager.logEvent(AnalyticsEvents.click_main_menu_latte_art)
            AlertUtil.showOkAlert(
                this,
                R.string.alert_under_construction_title,
                R.string.alert_under_construction_description
            )
        }
        itemFavorites.setOnClickListener {
            analyticsManager.logEvent(AnalyticsEvents.click_main_menu_favorites)
            globalNavigator.startFavoritesActivity(this)
        }
        btnInfo.setOnClickListener {
            analyticsManager.logEvent(AnalyticsEvents.click_main_menu_app_info)
            globalNavigator.startAppInfoActivity(this)
        }
        btnSettings.gone()
        btnSettings.setOnClickListener {
            analyticsManager.logEvent(AnalyticsEvents.click_main_menu_settings)
        }
    }

    override fun initViewModel() {
        viewModel.apply {
            observe(dishesFromCache, ::handleDishesFromCache)
            observe(itemCategoriesFromCache, ::handleItemCategoriesFromCache)
        }
    }

    private fun handleDishesFromCache(dishes: List<Dish>) {
        this.dishes.replaceAllWith(dishes)
    }

    private fun handleItemCategoriesFromCache(itemCategories: List<ItemCategory>) {
        this.itemCategories.replaceAllWith(itemCategories)
    }
}