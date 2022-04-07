package com.eratart.baristashandbook.presentationbase.itemslistactivity

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.eratart.baristashandbook.baseui.activity.BaseActivity
import com.eratart.baristashandbook.baseui.viewmodel.BaseViewModel
import com.eratart.baristashandbook.core.ext.gone
import com.eratart.baristashandbook.core.ext.visible
import com.eratart.baristashandbook.databinding.ActivityItemsListBinding
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory
import com.eratart.baristashandbook.presentationbase.itemslistactivity.recycler.ItemCategoryAdapter
import com.eratart.baristashandbook.presentationbase.itemslistactivity.recycler.swipe.SwipeController


abstract class BaseItemsListActivity<VM : BaseViewModel> :
    BaseActivity<VM, ActivityItemsListBinding>() {

    companion object {
        const val EXTRAS_SUBTITLE = "ItemsListActivity.EXTRAS_SUBTITLE"
        const val EXTRAS_ITEMS = "ItemsListActivity.EXTRAS_ITEMS"

        const val EXTRAS_CATEGORY = "ItemsListActivity.EXTRAS_CATEGORY"

        const val SEARCH_DEBOUNCE = 250L
    }

    abstract val titleRes: Int
    protected open var swipeEnabled: Boolean = false

    private val items by lazy { intent.getParcelableArrayListExtra<Item>(EXTRAS_ITEMS) ?: listOf() }
    private val subtitle by lazy { intent.getStringExtra(EXTRAS_SUBTITLE) }
    protected val category by lazy { intent.getParcelableExtra<ItemCategory>(EXTRAS_CATEGORY) }

    protected val sourceList by lazy { ArrayList<Any>(items) }
    protected val mutableList by lazy { mutableListOf<Any>() }
    protected val itemAdapter by lazy { ItemCategoryAdapter(mutableList) }

    override val binding by lazy { ActivityItemsListBinding.inflate(layoutInflater) }
    protected val rvItems by lazy { binding.rvItems }
    protected val appBar by lazy { binding.appBar }
    protected val layoutNotFound by lazy { binding.layoutNotFound.root }

    override fun initView() {
        appBar.init(this)
        appBar.initSearchBtn(SEARCH_DEBOUNCE) { searchString -> onSearch(searchString) }
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
                    is Item -> it.title.contains(searchString, true)
                    is ItemCategory -> it.title.contains(searchString, true)
                    else -> false
                }
            }
            showContent(filteredList)
        }
    }

    protected fun showContent(list: List<Any>, checkSame: Boolean = true) {
        if (list == mutableList && checkSame) return

        mutableList.clear()
        mutableList.addAll(list)
        itemAdapter.notifyDataSetChanged()

        if (list.isNotEmpty()) {
            layoutNotFound.gone()
            rvItems.scheduleLayoutAnimation()
        } else {
            layoutNotFound.visible()
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