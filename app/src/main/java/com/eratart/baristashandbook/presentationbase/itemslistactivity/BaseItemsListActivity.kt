package com.eratart.baristashandbook.presentationbase.itemslistactivity

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.eratart.baristashandbook.core.constants.LongConstants.SEARCH_DEBOUNCE
import com.eratart.baristashandbook.core.ext.gone
import com.eratart.baristashandbook.core.ext.replaceAllWith
import com.eratart.baristashandbook.core.ext.visibleWithAlpha
import com.eratart.baristashandbook.databinding.ActivityItemsListBinding
import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory
import com.eratart.baristashandbook.domain.model.NewsBot
import com.eratart.baristashandbook.presentationbase.itemslistactivity.recycler.ItemsListAdapter
import com.eratart.baristashandbook.presentationbase.itemslistactivity.recycler.swipe.SwipeController

abstract class BaseItemsListActivity<VM : BaseViewModel> :
    BaseActivity<VM, ActivityItemsListBinding>() {

    companion object {
        const val EXTRAS_SUBTITLE = "ItemsListActivity.EXTRAS_SUBTITLE"
        const val EXTRAS_ITEMS = "ItemsListActivity.EXTRAS_ITEMS"

        const val EXTRAS_CATEGORY = "ItemsListActivity.EXTRAS_CATEGORY"
    }

    abstract val titleRes: Int
    abstract val searchAnalyticsEvent: String
    protected open var swipeEnabled: Boolean = false

    private val items by lazy { intent.getParcelableArrayListExtra<Item>(EXTRAS_ITEMS) ?: listOf() }
    private val subtitle by lazy { intent.getStringExtra(EXTRAS_SUBTITLE) }
    protected val category by lazy { intent.getParcelableExtra<ItemCategory>(EXTRAS_CATEGORY) }

    protected val sourceList by lazy { ArrayList<Any>(items) }
    protected val mutableList by lazy { mutableListOf<Any>() }
    protected val itemAdapter by lazy { ItemsListAdapter(mutableList) }

    override val binding by lazy { ActivityItemsListBinding.inflate(layoutInflater) }
    private val rvItems by lazy { binding.rvItems }
    private val appBar by lazy { binding.appBar }
    private val layoutNotFound by lazy { binding.layoutNotFound.root }

    override fun initView() {
        appBar.init(this)
        appBar.initSearchBtn(SEARCH_DEBOUNCE, searchAnalyticsEvent) { searchString ->
            onSearch(searchString)
        }
        appBar.setTitleText(getString(titleRes))
        appBar.setSubtitleText(subtitle)

        mutableList.addAll(sourceList)
        rvItems.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = itemAdapter
        }
        rvItems.scheduleLayoutAnimation()

        itemAdapter.setOnItemClick { item, i ->
            onItemClick(item, i)
        }

        if (swipeEnabled) {
            val swipeControllerCallback = object : SwipeController.Callback {
                override fun onSwipedAway(position: Int) {
                    itemAdapter.getItem(position)?.run {
                        if (this is Item) {
                            onItemSwipedAway(this, position)
                        }
                    }
                }
            }
            val swipeController = SwipeController(this, swipeControllerCallback)
            ItemTouchHelper(swipeController).attachToRecyclerView(rvItems)
        }
    }

    abstract fun onItemClick(item: Any, pos: Int)
    protected open fun onItemSwipedAway(item: Item, pos: Int) {}

    protected open fun onSearch(searchString: String) {
        if (searchString.isEmpty()) {
            showContent(sourceList)
        } else {
            val filteredList = sourceList.filter {
                when (it) {
                    is Dish -> it.title.contains(searchString, true)
                    is Item -> it.title.contains(searchString, true)
                    is ItemCategory -> it.title.contains(searchString, true)
                    is NewsBot -> it.title.contains(searchString, true) || it.text.orEmpty()
                        .contains(searchString, true)
                    else -> false
                }
            }
            showContent(filteredList)
        }
    }

    protected fun showContent(list: List<Any>, checkSame: Boolean = true) {
        if (list == mutableList && checkSame) return

        mutableList.replaceAllWith(list)
        itemAdapter.notifyDataSetChanged()

        if (list.isNotEmpty()) {
            rvItems.scheduleLayoutAnimation()
        }
        showEmptyState()
    }

    protected fun showEmptyState() {
        if (mutableList.isNotEmpty()) {
            layoutNotFound.gone()
        } else {
            layoutNotFound.visibleWithAlpha()
        }
    }

    override fun onBackPressed() {
        if (appBar.isSearchActive) {
            appBar.deactivateSearch()
        } else {
            super.onBackPressed()
        }
    }
}