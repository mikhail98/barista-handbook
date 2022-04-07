package com.eratart.baristashandbook.presentation.itemdetails.view.recycler.ingredients

import android.view.ViewGroup
import com.eratart.baristashandbook.baseui.view.recyclerview.BaseRecyclerAdapter
import com.eratart.baristashandbook.baseui.view.recyclerview.BaseRecyclerViewHolder
import com.eratart.baristashandbook.databinding.ItemIngredientBinding
import com.eratart.baristashandbook.domain.model.Ingredient

class IngredientAdapter(viewModels: MutableList<Ingredient>) :
    BaseRecyclerAdapter<Ingredient>(viewModels) {

    override fun getBindingViewHolder(
        viewType: Int, parent: ViewGroup
    ): BaseRecyclerViewHolder<Ingredient, ItemIngredientBinding> {
        val view = ItemIngredientBinding.inflate(getInflater(parent), parent, false)
        return IngredientViewHolder(view)
    }
}