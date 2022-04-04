package com.eratart.baristashandbook.baseui.view.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.eratart.baristashandbook.core.constants.IntConstants
import com.eratart.baristashandbook.core.ext.replaceAllWith
import java.util.*

abstract class BaseRecyclerAdapter<VM : Any>(
    private var viewModels: MutableList<VM>
) :
    RecyclerView.Adapter<BaseRecyclerViewHolder<VM, *>>() {

    private var onItemClick: ((VM, Int) -> Unit)? = null
    fun setOnItemClick(listener: (VM, Int) -> Unit) {
        this.onItemClick = listener
    }

    protected abstract fun getBindingViewHolder(
        viewType: Int,
        parent: ViewGroup
    ): BaseRecyclerViewHolder<VM, *>

    open fun getItem(position: Int): VM? {
        return if (position - 1 <= viewModels.size) {
            viewModels[position]
        } else {
            null
        }
    }

    open fun getItems(): List<VM> = viewModels

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<VM, *>, position: Int) {
        val viewModel = getItem(position)
        holder.setOnItemClick(onItemClick)
        viewModel?.apply {
            holder.bindItem(viewModel)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        getBindingViewHolder(viewType, parent)

    override fun getItemCount() = viewModels.size

    override fun getItemId(position: Int) = viewModels[position]
        .hashCode()
        .toLong()

    protected fun getInflater(parent: ViewGroup): LayoutInflater =
        LayoutInflater.from(parent.context)

    protected fun createView(viewGroup: ViewGroup, @LayoutRes layoutRes: Int): View {
        val context = viewGroup.context
        val layoutInflater = LayoutInflater.from(context)
        return layoutInflater.inflate(layoutRes, viewGroup, false)
    }

    fun insertItem(item: VM, position: Int = itemCount) {
        viewModels.add(position, item)
        notifyItemInserted(position)
    }

    fun removeAtPosition(position: Int) {
        viewModels.removeAt(position)
        notifyItemRemoved(position)
    }

    fun replaceAll(newItems: List<VM>) {
        viewModels.replaceAllWith(newItems)
        notifyDataSetChanged()
    }

    fun replaceItem(item: VM, position: Int) {
        viewModels[position] = item
        notifyItemChanged(position)
    }

    fun changePosition(fromPosition: Int, toPosition: Int) {
        if (fromPosition == toPosition) return
        if (fromPosition == IntConstants.MINUS_ONE || toPosition == IntConstants.MINUS_ONE) return
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(viewModels, i, i + IntConstants.ONE)
            }
        } else {
            for (i in fromPosition downTo toPosition + IntConstants.ONE) {
                Collections.swap(viewModels, i, i - IntConstants.ONE)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }
}