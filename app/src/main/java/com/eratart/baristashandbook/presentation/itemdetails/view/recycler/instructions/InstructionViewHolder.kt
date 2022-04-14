package com.eratart.baristashandbook.presentation.itemdetails.view.recycler.instructions

import com.eratart.baristashandbook.baseui.view.recyclerview.BaseRecyclerViewHolder
import com.eratart.baristashandbook.core.constants.IntConstants
import com.eratart.baristashandbook.core.util.TextViewUrlUtil.setLinksClickable
import com.eratart.baristashandbook.core.util.markdown.MarkdownUtil.renderMD
import com.eratart.baristashandbook.databinding.ItemInstructionBinding

class InstructionViewHolder(
    private val binding: ItemInstructionBinding,
    private val listener: ILinkClickListener?
) :
    BaseRecyclerViewHolder<String, ItemInstructionBinding>(binding) {

    private val tvInstructionIndex by lazy { binding.tvInstructionIndex }
    private val tvInstruction by lazy { binding.tvInstruction }

    override fun bindItem(item: String) {
        super.bindItem(item)
        val position = adapterPosition + IntConstants.ONE
        tvInstructionIndex.text = position.toString()

        tvInstruction.renderMD(item)
        tvInstruction.setLinksClickable { link -> listener?.onItemClick(link) }
    }

    interface ILinkClickListener {
        fun onItemClick(itemId: String)
    }
}