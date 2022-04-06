package com.eratart.baristashandbook.presentation.itemscategorieslist.view

import androidx.recyclerview.widget.LinearLayoutManager
import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.core.mock.ItemCategoriesMock
import com.eratart.baristashandbook.core.mock.ItemsMock
import com.eratart.baristashandbook.databinding.ActivityItemsListBinding
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory
import com.eratart.baristashandbook.presentation.itemscategorieslist.view.recycler.ItemCategoryAdapter
import com.eratart.baristashandbook.presentation.itemscategorieslist.viewmodel.ItemsCategoriesListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemsCategoriesListActivity :
    BaseActivity<ItemsCategoriesListViewModel, ActivityItemsListBinding>() {

    override val viewModel: ItemsCategoriesListViewModel by viewModel()
    override val binding by lazy { ActivityItemsListBinding.inflate(layoutInflater) }
    private val rvItems by lazy { binding.rvItems }
    private val appBar by lazy { binding.appBar }

    private val sourceList by lazy {
        val mutableListOfAny = mutableListOf<Any>()
        mutableListOfAny.addAll(ItemCategoriesMock.getCategories(7))
        mutableListOfAny.addAll(ItemsMock.getItems(7))
        mutableListOfAny
    }
    private val mutableList by lazy { mutableListOf<Any>() }
    private val itemAdapter by lazy { ItemCategoryAdapter(mutableList) }

    override fun initView() {
        appBar.initBackBtn(this)
        appBar.initMoreBtn { }
        appBar.initSearchBtn { searchString ->
            if (searchString.isEmpty()) {
                resetSearch()
            } else {
                mutableList.clear()
                val filteredList = sourceList.filter {
                    when (it) {
                        is Item -> it.title.contains(searchString, true)
                        is ItemCategory -> it.title.contains(searchString, true)
                        else -> false
                    }
                }
                mutableList.addAll(filteredList)
                itemAdapter.notifyDataSetChanged()
            }
        }
        appBar.setOnSearchClosedListener {
            resetSearch()
        }

        mutableList.addAll(sourceList)
        rvItems.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = itemAdapter
        }

        itemAdapter.setOnItemClick { itemCategory, i ->

        }
    }

    private fun resetSearch() {
        mutableList.clear()
        mutableList.addAll(sourceList)
        itemAdapter.notifyDataSetChanged()
    }

    override fun initViewModel() {
    }
}