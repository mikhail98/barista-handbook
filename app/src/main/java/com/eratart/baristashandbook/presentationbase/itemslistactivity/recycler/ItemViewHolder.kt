package com.eratart.baristashandbook.presentationbase.itemslistactivity.recycler

import androidx.viewbinding.ViewBinding
import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.baseui.view.recyclerview.BaseRecyclerViewHolder
import com.eratart.baristashandbook.core.ext.loadImageWithGlideCircle
import com.eratart.baristashandbook.databinding.ItemDrinkBinding
import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item

class ItemViewHolder(private val binding: ItemDrinkBinding) :
    BaseRecyclerViewHolder<Any, ViewBinding>(binding) {

    private val ivDrink by lazy { binding.ivDrink }
    private val tvTitle by lazy { binding.tvTitle }

    override fun bindItem(item: Any) {
        super.bindItem(item)
        when (item) {
            is Item -> {
                tvTitle.text = item.title
                val photos = item.photos
                if (photos.isNotEmpty()) {
                    ivDrink.loadImageWithGlideCircle(item.photos.first())
                } else {
                    ivDrink.loadImageWithGlideCircle(R.drawable.ic_drink_placeholder)
                }
            }

            is Dish -> {
                tvTitle.text = item.title
                val photos = item.photos
                if (photos.isNotEmpty()) {
                    ivDrink.loadImageWithGlideCircle(item.photos.first())
                } else {
                    ivDrink.loadImageWithGlideCircle(R.drawable.ic_placeholder)
                }
            }
        }

    }
}