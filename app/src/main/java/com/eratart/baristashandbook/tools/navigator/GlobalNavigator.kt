package com.eratart.baristashandbook.tools.navigator

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory
import com.eratart.baristashandbook.domain.model.NewsBot
import com.eratart.baristashandbook.presentation.artinstructions.view.ArtInstructionsActivity
import com.eratart.baristashandbook.presentation.dishdetails.view.DishDetailsActivity
import com.eratart.baristashandbook.presentation.disheslist.view.DishesListActivity
import com.eratart.baristashandbook.presentation.favorites.view.FavoritesActivity
import com.eratart.baristashandbook.presentation.itemdetails.view.ItemDetailsActivity
import com.eratart.baristashandbook.presentation.itemscategorieslist.view.ItemsCategoriesListActivity
import com.eratart.baristashandbook.presentation.itemslist.view.ItemsListActivity
import com.eratart.baristashandbook.presentation.mainmenu.view.MainMenuActivity
import com.eratart.baristashandbook.presentation.news_details.view.NewsDetailsActivity
import com.eratart.baristashandbook.presentation.news_list.view.NewsListActivity
import com.eratart.baristashandbook.presentation.onboarding.view.OnboardingActivity
import com.eratart.baristashandbook.presentation.settings.view.SettingsActivity
import com.eratart.baristashandbook.presentationbase.itemslistactivity.BaseItemsListActivity


class GlobalNavigator : IGlobalNavigator {

    override fun startArtInstructionActivity(activity: Activity) {
        activity.startActivity(Intent(activity, ArtInstructionsActivity::class.java))
    }

    override fun startDishDetailsActivity(activity: Activity, dish: Dish) {
        val intent = Intent(activity, DishDetailsActivity::class.java).apply {
            putExtra(DishDetailsActivity.EXTRAS_DISH, dish)
        }
        activity.startActivity(intent)
    }

    override fun startDishesListActivity(activity: Activity, dishes: List<Dish>) {
        val intent = Intent(activity, DishesListActivity::class.java).apply {
            putParcelableArrayListExtra(BaseItemsListActivity.EXTRAS_ITEMS, ArrayList(dishes))
        }
        activity.startActivity(intent)
    }

    override fun startFavoritesActivity(activity: Activity) {
        activity.startActivity(Intent(activity, FavoritesActivity::class.java))
    }

    override fun startItemDetailsActivity(activity: Activity, item: Item, category: ItemCategory?) {
        val intent = Intent(activity, ItemDetailsActivity::class.java).apply {
            putExtra(ItemDetailsActivity.EXTRAS_ITEM, item)
            putExtra(ItemDetailsActivity.EXTRAS_ITEM_CATEGORY, category)
        }
        activity.startActivity(intent)
    }

    override fun startItemsCategoriesListActivity(activity: Activity, list: List<ItemCategory>) {
        val intent = Intent(activity, ItemsCategoriesListActivity::class.java).apply {
            putExtra(BaseItemsListActivity.EXTRAS_ITEMS, ArrayList(list))
        }
        activity.startActivity(intent)
    }

    override fun startItemsListActivity(
        activity: Activity, subtitle: String, items: List<Item>, category: ItemCategory?
    ) {
        val intent = Intent(activity, ItemsListActivity::class.java).apply {
            putExtra(BaseItemsListActivity.EXTRAS_SUBTITLE, subtitle)
            putExtra(BaseItemsListActivity.EXTRAS_CATEGORY, category)
            putParcelableArrayListExtra(BaseItemsListActivity.EXTRAS_ITEMS, ArrayList(items))
        }
        activity.startActivity(intent)
    }

    override fun startMainMenuActivity(activity: Activity) {
        activity.startActivity(Intent(activity, MainMenuActivity::class.java))
    }

    override fun startNewsListActivity(activity: Activity) {
        activity.startActivity(Intent(activity, NewsListActivity::class.java))
    }

    override fun startNewsDetailsActivity(activity: Activity, newsBot: NewsBot) {
        val intent = Intent(activity, NewsDetailsActivity::class.java).apply {
            putExtra(NewsDetailsActivity.EXTRAS_NEWS, newsBot)
        }
        activity.startActivity(intent)
    }

    override fun startOnboardingActivity(activity: Activity) {
        activity.startActivity(Intent(activity, OnboardingActivity::class.java))
    }

    override fun startSettingsActivity(activity: Activity) {
        activity.startActivity(Intent(activity, SettingsActivity::class.java))
    }

    override fun openInBrowser(activity: Activity, url: String) {
        activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}