package com.eratart.baristashandbook.presentation.itemscategorieslist.view

import android.os.Bundle
import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.core.mock.ItemCategoriesMock
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory
import com.eratart.baristashandbook.presentation.itemscategorieslist.viewmodel.ItemsCategoriesListViewModel
import com.eratart.baristashandbook.presentationbase.itemslistactivity.BaseItemsListActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemsCategoriesListActivity : BaseItemsListActivity<ItemsCategoriesListViewModel>() {

    override val titleRes = R.string.main_menu_drinks

    override val viewModel: ItemsCategoriesListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        sourceList.addAll(ItemCategoriesMock.getCategories(4))
        super.onCreate(savedInstanceState)
    }

    override fun initViewModel() {

    }

    override fun onSearch(searchString: String) {
        if (searchString.isEmpty()) {
            resetSearch()
        } else {
            mutableList.clear()

            sourceList.filterIsInstance<ItemCategory>().forEach { category ->
                if (category.title.contains(searchString, true)) {
                    mutableList.add(category)
                }
            }
            sourceList.filterIsInstance<ItemCategory>().forEach { category ->
                category.drinks.forEach { drink ->
                    if (drink.title.contains(searchString, true)) {
                        mutableList.add(drink)
                    }
                }
            }
            itemAdapter.notifyDataSetChanged()
        }

        rvItems.scheduleLayoutAnimation()
    }

    override fun onItemClick(item: Any, pos: Int) {
        when (item) {
            is Item -> {
                globalNavigator.startItemDetailsActivity(this)
            }
            is ItemCategory -> {
                globalNavigator.startItemsListActivity(this, item.title, item.drinks)
            }
        }
    }
}