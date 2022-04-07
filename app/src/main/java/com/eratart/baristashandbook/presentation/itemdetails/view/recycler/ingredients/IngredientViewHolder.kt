package com.eratart.baristashandbook.presentation.itemdetails.view.recycler.ingredients

import com.eratart.baristashandbook.baseui.view.recyclerview.BaseRecyclerViewHolder
import com.eratart.baristashandbook.databinding.ItemIngredientBinding
import com.eratart.baristashandbook.domain.model.Ingredient

class IngredientViewHolder(private val binding: ItemIngredientBinding) :
    BaseRecyclerViewHolder<Ingredient, ItemIngredientBinding>(binding) {

    private val tvIngredientTitle by lazy { binding.tvIngredientTitle }
    private val tvIngredientVolume by lazy { binding.tvIngredientVolume }

    override fun bindItem(item: Ingredient) {
        super.bindItem(item)
        tvIngredientTitle.text = item.title
        tvIngredientVolume.text = item.volume
    }
}