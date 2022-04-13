package com.eratart.baristashandbook.presentation.itemdetails.view.recycler.instructions

import android.view.ViewGroup
import com.eratart.baristashandbook.baseui.view.recyclerview.BaseRecyclerAdapter
import com.eratart.baristashandbook.baseui.view.recyclerview.BaseRecyclerViewHolder
import com.eratart.baristashandbook.databinding.ItemInstructionBinding

class InstructionAdapter(viewModels: MutableList<String>) :
    BaseRecyclerAdapter<String>(viewModels) {

    private var linkClickListener: InstructionViewHolder.ILinkClickListener? = null
    fun setLinkClickListener(listener: InstructionViewHolder.ILinkClickListener){
        linkClickListener = listener
    }

    override fun getBindingViewHolder(
        viewType: Int, parent: ViewGroup
    ): BaseRecyclerViewHolder<String, ItemInstructionBinding> {
        val view = ItemInstructionBinding.inflate(getInflater(parent), parent, false)
        return InstructionViewHolder(view, linkClickListener)
    }
}