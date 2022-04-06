package com.eratart.baristashandbook.tools.navigator

import android.app.Activity
import android.content.Intent
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.presentation.artinstructions.view.ArtInstructionsActivity
import com.eratart.baristashandbook.presentation.dishdetails.view.DishDetailsActivity
import com.eratart.baristashandbook.presentation.disheslist.view.DishesListActivity
import com.eratart.baristashandbook.presentation.favorites.view.FavoritesActivity
import com.eratart.baristashandbook.presentation.itemdetails.view.ItemDetailsActivity
import com.eratart.baristashandbook.presentation.itemscategorieslist.view.ItemsCategoriesListActivity
import com.eratart.baristashandbook.presentation.itemslist.view.ItemsListActivity
import com.eratart.baristashandbook.presentation.mainmenu.view.MainMenuActivity
import com.eratart.baristashandbook.presentation.news.view.NewsActivity
import com.eratart.baristashandbook.presentation.onboarding.view.OnboardingActivity
import com.eratart.baristashandbook.presentation.settings.view.SettingsActivity
import com.eratart.baristashandbook.presentationbase.itemslistactivity.BaseItemsListActivity

class GlobalNavigator : IGlobalNavigator {

    override fun startArtInstructionActivity(activity: Activity) {
        activity.startActivity(Intent(activity, ArtInstructionsActivity::class.java))
    }

    override fun startDishDetailsActivity(activity: Activity) {
        activity.startActivity(Intent(activity, DishDetailsActivity::class.java))
    }

    override fun startDishesListActivity(activity: Activity) {
        activity.startActivity(Intent(activity, DishesListActivity::class.java))
    }

    override fun startFavoritesActivity(activity: Activity) {
        activity.startActivity(Intent(activity, FavoritesActivity::class.java))
    }

    override fun startItemDetailsActivity(activity: Activity) {
        activity.startActivity(Intent(activity, ItemDetailsActivity::class.java))
    }

    override fun startItemsCategoriesListActivity(activity: Activity) {
        activity.startActivity(Intent(activity, ItemsCategoriesListActivity::class.java))
    }

    override fun startItemsListActivity(activity: Activity, subtitle: String, items: List<Item>) {
        val intent = Intent(activity, ItemsListActivity::class.java).apply {
            putExtra(BaseItemsListActivity.EXTRAS_SUBTITLE, subtitle)
            putParcelableArrayListExtra(BaseItemsListActivity.EXTRAS_ITEMS, ArrayList(items))
        }
        activity.startActivity(intent)
    }

    override fun startMainMenuActivity(activity: Activity) {
        activity.startActivity(Intent(activity, MainMenuActivity::class.java))
    }

    override fun startNewsActivity(activity: Activity) {
        activity.startActivity(Intent(activity, NewsActivity::class.java))
    }

    override fun startOnboardingActivity(activity: Activity) {
        activity.startActivity(Intent(activity, OnboardingActivity::class.java))
    }

    override fun startSettingsActivity(activity: Activity) {
        activity.startActivity(Intent(activity, SettingsActivity::class.java))
    }
}