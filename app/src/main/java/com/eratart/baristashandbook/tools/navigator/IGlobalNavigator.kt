package com.eratart.baristashandbook.tools.navigator

import android.app.Activity

interface IGlobalNavigator {

    fun startArtInstructionActivity(activity: Activity)

    fun startDishDetailsActivity(activity: Activity)

    fun startDishesListActivity(activity: Activity)

    fun startFavoritesActivity(activity: Activity)

    fun startItemDetailsActivity(activity: Activity)

    fun startItemsListActivity(activity: Activity)

    fun startMainMenuActivity(activity: Activity)

    fun startNewsActivity(activity: Activity)

    fun startOnboardingActivity(activity: Activity)

    fun startSettingsActivity(activity: Activity)

}