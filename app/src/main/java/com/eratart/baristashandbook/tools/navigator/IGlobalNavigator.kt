package com.eratart.baristashandbook.tools.navigator

import android.app.Activity
import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory

interface IGlobalNavigator {

    fun startArtInstructionActivity(activity: Activity)

    fun startDishDetailsActivity(activity: Activity, dish: Dish)

    fun startDishesListActivity(activity: Activity)

    fun startFavoritesActivity(activity: Activity)

    fun startItemDetailsActivity(activity: Activity, item: Item, category: ItemCategory?)

    fun startItemsCategoriesListActivity(activity: Activity)

    fun startItemsListActivity(
        activity: Activity, subtitle: String, items: List<Item>, category: ItemCategory?
    )

    fun startMainMenuActivity(activity: Activity)

    fun startNewsActivity(activity: Activity)

    fun startOnboardingActivity(activity: Activity)

    fun startSettingsActivity(activity: Activity)

}