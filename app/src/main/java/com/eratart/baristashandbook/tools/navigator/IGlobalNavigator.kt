package com.eratart.baristashandbook.tools.navigator

import android.app.Activity
import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory
import com.eratart.baristashandbook.domain.model.NewsBot

interface IGlobalNavigator {

    fun startAppInfoActivity(activity: Activity)

    fun startArtInstructionActivity(activity: Activity)

    fun startDishDetailsActivity(activity: Activity, dish: Dish)

    fun startDishesListActivity(activity: Activity, dishes: List<Dish>)

    fun startFavoritesActivity(activity: Activity)

    fun startItemDetailsActivity(activity: Activity, item: Item)

    fun startItemsCategoriesListActivity(activity: Activity, list: List<ItemCategory>)

    fun startItemsListActivity(
        activity: Activity, subtitle: String, items: List<Item>, category: ItemCategory?
    )

    fun startMainMenuActivity(activity: Activity)

    fun startNewsListActivity(activity: Activity)

    fun startNewsDetailsActivity(activity: Activity, newsBot: NewsBot)

    fun startOnboardingActivity(activity: Activity)

    fun startSettingsActivity(activity: Activity)

    fun openInBrowser(activity: Activity, url: String)

}