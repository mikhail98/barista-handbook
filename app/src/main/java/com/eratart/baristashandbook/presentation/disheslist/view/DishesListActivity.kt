package com.eratart.baristashandbook.presentation.disheslist.view

import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.domain.firebase.AnalyticsEvents
import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.presentation.disheslist.di.dishesListModule
import com.eratart.baristashandbook.presentation.disheslist.viewmodel.DishesListViewModel
import com.eratart.baristashandbook.presentationbase.itemslistactivity.BaseItemsListActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DishesListActivity : BaseItemsListActivity<DishesListViewModel>() {

    override val titleRes = R.string.main_menu_dishes
    override val viewModel: DishesListViewModel by viewModel()
    override val koinModules = listOf(dishesListModule)

    override val searchAnalyticsEvent by lazy { AnalyticsEvents.click_dishes_list_search }

    override fun initViewModel() {

    }

    override fun onItemClick(item: Any, pos: Int) {
        when (item) {
            is Dish -> {
                analyticsManager.logEvent(AnalyticsEvents.click_dishes_list_item)
                globalNavigator.startDishDetailsActivity(this, item)
            }
        }
    }
}