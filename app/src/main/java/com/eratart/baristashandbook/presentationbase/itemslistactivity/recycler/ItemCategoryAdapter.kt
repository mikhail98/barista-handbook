package com.eratart.baristashandbook.presentationbase.itemslistactivity.recycler

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.eratart.baristashandbook.baseui.view.recyclerview.BaseRecyclerAdapter
import com.eratart.baristashandbook.baseui.view.recyclerview.BaseRecyclerViewHolder
import com.eratart.baristashandbook.databinding.ItemDrinkBinding
import com.eratart.baristashandbook.databinding.ItemDrinkCategoryBinding
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory

class ItemCategoryAdapter(viewModels: MutableList<Any>) :
    BaseRecyclerAdapter<Any>(viewModels) {

    companion object {
        private const val TYPE_CATEGORY = 0
        private const val TYPE_DRINK = 1
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
            else -> throw RuntimeException("Unsupported type")
        }
        return viewHolder
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ItemCategory -> TYPE_CATEGORY
            is Item -> TYPE_DRINK
            else -> throw RuntimeException("Unsupported type")
        }
    }
}