package com.eratart.baristashandbook.presentation.itemdetails.view.recycler.instructions

import com.eratart.baristashandbook.baseui.view.recyclerview.BaseRecyclerViewHolder
import com.eratart.baristashandbook.core.constants.IntConstants
import com.eratart.baristashandbook.databinding.ItemInstructionBinding

class InstructionViewHolder(private val binding: ItemInstructionBinding) :
    BaseRecyclerViewHolder<String, ItemInstructionBinding>(binding) {

    private val tvInstructionIndex by lazy { binding.tvInstructionIndex }
    private val tvInstruction by lazy { binding.tvInstruction }

    override fun bindItem(item: String) {
        super.bindItem(item)
        val position = adapterPosition + IntConstants.ONE
        tvInstructionIndex.text = position.toString()
        tvInstruction.text = item
    }
}