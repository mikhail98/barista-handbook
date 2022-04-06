package com.eratart.baristashandbook.tools.navigator

import android.app.Activity
import com.eratart.baristashandbook.domain.model.Item

interface IGlobalNavigator {

    fun startArtInstructionActivity(activity: Activity)

    fun startDishDetailsActivity(activity: Activity)

    fun startDishesListActivity(activity: Activity)

    fun startFavoritesActivity(activity: Activity)

    fun startItemDetailsActivity(activity: Activity)

    fun startItemsCategoriesListActivity(activity: Activity)

    fun startItemsListActivity(activity: Activity, subtitle: String, items: List<Item>)

    fun startMainMenuActivity(activity: Activity)

    fun startNewsActivity(activity: Activity)

    fun startOnboardingActivity(activity: Activity)

    fun startSettingsActivity(activity: Activity)

}