package com.eratart.baristashandbook.presentationbase.itemslistactivity

import androidx.recyclerview.widget.LinearLayoutManager
import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.eratart.baristashandbook.databinding.ActivityItemsListBinding
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory
import com.eratart.baristashandbook.presentation.itemscategorieslist.view.recycler.ItemCategoryAdapter

abstract class BaseItemsListActivity<VM : BaseViewModel> :
    BaseActivity<VM, ActivityItemsListBinding>() {

    companion object {
        const val EXTRAS_SUBTITLE = "ItemsListActivity.EXTRAS_SUBTITLE"
        const val EXTRAS_ITEMS = "ItemsListActivity.EXTRAS_ITEMS"
    }

    abstract val titleRes: Int
    private val subtitle by lazy { intent.getStringExtra(EXTRAS_SUBTITLE) }
    private val items by lazy { intent.getParcelableArrayListExtra<Item>(EXTRAS_ITEMS) ?: listOf() }

    protected val sourceList by lazy { ArrayList<Any>(items) }
    protected val mutableList by lazy { mutableListOf<Any>() }
    protected val itemAdapter by lazy { ItemCategoryAdapter(mutableList) }

    override val binding by lazy { ActivityItemsListBinding.inflate(layoutInflater) }
    protected val rvItems by lazy { binding.rvItems }
    protected val appBar by lazy { binding.appBar }

    override fun initView() {
        appBar.initBackBtn(this)
        appBar.initMoreBtn { }
        appBar.initSearchBtn { searchString -> onSearch(searchString) }

        appBar.setTitleText(getString(titleRes))

        subtitle?.run {
            appBar.setSubtitleText(this)
        }

        appBar.setOnSearchClosedListener {
            resetSearch()
        }

        mutableList.addAll(sourceList)
        rvItems.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = itemAdapter
        }
        rvItems.scheduleLayoutAnimation()

        itemAdapter.setOnItemClick { item, i ->
            onItemClick(item, i)
        }
    }

    abstract fun onItemClick(item: Any, pos: Int)

    protected open fun onSearch(searchString: String) {
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

        rvItems.scheduleLayoutAnimation()
    }

    protected fun resetSearch() {
        mutableList.clear()
        mutableList.addAll(sourceList)
        itemAdapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        if (appBar.isSearchActive) {
            appBar.deactivateSearch(this)
        } else {
            super.onBackPressed()
        }
    }
}