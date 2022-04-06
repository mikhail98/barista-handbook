package com.eratart.baristashandbook.presentation.itemscategorieslist.view.recycler

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.eratart.baristashandbook.baseui.view.recyclerview.BaseRecyclerAdapter
import com.eratart.baristashandbook.baseui.view.recyclerview.BaseRecyclerViewHolder
import com.eratart.baristashandbook.databinding.ItemDrinkBinding
import com.eratart.baristashandbook.databinding.ItemDrinkCategoryBinding
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory

class ItemCategoryAdapter(viewModels: MutableList<*>) :
    BaseRecyclerAdapter<Any>(viewModels as MutableList<Any>) {

    companion object {
        private const val TYPE_CATEGORY = 0
        private const val TYPE_DRINK = 1
        private const val TYPE_HEADER = 1
    }

    override fun getBindingViewHolder(
        viewType: Int, parent: ViewGroup
    ): BaseRecyclerViewHolder<Any, ViewBinding> {
        val viewHolder = when (viewType) {
            TYPE_CATEGORY -> {
                val view = ItemDrinkCategoryBinding.inflate(getInflater(parent), parent, false)
                ItemCategoryViewHolder(view)
            }
            TYPE_DRINK -> {
                val view = ItemDrinkBinding.inflate(getInflater(parent), parent, false)
                ItemViewHolder(view)
            }
            else -> {
                val view = ItemDrinkCategoryBinding.inflate(getInflater(parent), parent, false)
                ItemCategoryViewHolder(view)
            }
        }
        return viewHolder as BaseRecyclerViewHolder<Any, ViewBinding>
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when (item) {
            is ItemCategory -> TYPE_CATEGORY
            is Item -> TYPE_DRINK
            else -> TYPE_HEADER
        }
    }
}