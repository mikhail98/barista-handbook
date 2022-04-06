package com.eratart.baristashandbook.presentation.itemscategorieslist.view.recycler

import androidx.viewbinding.ViewBinding
import com.eratart.baristashandbook.baseui.view.recyclerview.BaseRecyclerViewHolder
import com.eratart.baristashandbook.databinding.ItemDrinkCategoryBinding
import com.eratart.baristashandbook.domain.model.ItemCategory

class ItemCategoryViewHolder(private val binding: ItemDrinkCategoryBinding) :
    BaseRecyclerViewHolder<Any, ViewBinding>(binding) {


    private val tvCount by lazy { binding.tvItemsCount }
    private val tvTitle by lazy { binding.tvTitle }

    override fun bindItem(item: Any) {
        super.bindItem(item)
        item as ItemCategory
        tvTitle.text = item.title
        tvCount.text = item.count.toString()
    }
}