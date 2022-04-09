package com.eratart.baristashandbook.presentation.itemscategorieslist.view

import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory
import com.eratart.baristashandbook.presentation.itemscategorieslist.viewmodel.ItemsCategoriesListViewModel
import com.eratart.baristashandbook.presentationbase.itemslistactivity.BaseItemsListActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemsCategoriesListActivity : BaseItemsListActivity<ItemsCategoriesListViewModel>() {

    override val titleRes = R.string.main_menu_drinks
    override val viewModel: ItemsCategoriesListViewModel by viewModel()

    override fun initViewModel() {

    }

    override fun onSearch(searchString: String) {
        if (searchString.isEmpty()) {
            showContent(sourceList)
        } else {
            val filteredList = mutableListOf<Any>()
            sourceList.filterIsInstance<ItemCategory>().forEach { category ->
                if (category.title.contains(searchString, true)) {
                    filteredList.add(category)
                }
            }
            sourceList.filterIsInstance<ItemCategory>().forEach { category ->
                category.drinks.forEach { drink ->
                    if (drink.title.contains(searchString, true)) {
                        filteredList.add(drink)
                    }
                }
            }
            showContent(filteredList)
        }

    }

    override fun onItemClick(item: Any, pos: Int) {
        when (item) {
            is Item -> {
                val category = sourceList
                    .filterIsInstance<ItemCategory>()
                    .find { category -> category.drinks.contains(item) }
                globalNavigator.startItemDetailsActivity(this, item, category)
            }
            is ItemCategory -> {
                globalNavigator.startItemsListActivity(this, item.title, item.drinks, item)
            }
        }
    }
}