package com.eratart.baristashandbook.presentation.favorites.view

import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.core.ext.observe
import com.eratart.baristashandbook.core.ext.replaceAllWith
import com.eratart.baristashandbook.core.mock.ItemCategoriesMock
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.presentation.favorites.viewmodel.FavoritesViewModel
import com.eratart.baristashandbook.presentationbase.itemslistactivity.BaseItemsListActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesActivity : BaseItemsListActivity<FavoritesViewModel>() {

    override val titleRes = R.string.main_menu_favorites
    override var swipeEnabled = true
    override val viewModel: FavoritesViewModel by viewModel()

    override fun initViewModel() {
        viewModel.apply {
            observe(favoritesList, ::handleFavorites)
        }
    }

    private fun handleFavorites(favorites: List<Item>) {
        sourceList.replaceAllWith(favorites.asReversed())
        showContent(favorites.asReversed())
    }

    override fun onItemClick(item: Any, pos: Int) {
        when (item) {
            is Item -> {
                val category = ItemCategoriesMock.getCategories()
                    .find { category -> category.drinks.contains(item) }
                globalNavigator.startItemDetailsActivity(this, item, category)
            }
        }
    }

    override fun onItemSwipedAway(item: Item, pos: Int) {
        itemAdapter.removeAtPosition(pos)
        viewModel.removeFromFavorites(item)
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchFavorites()
    }
}