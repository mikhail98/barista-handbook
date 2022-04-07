package com.eratart.baristashandbook.presentationbase.itemslistactivity.recycler.swipe

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.core.constants.IntConstants
import com.eratart.baristashandbook.core.ext.dpToPx
import kotlin.math.abs

class SwipeController(context: Context, private val callback: Callback) :
    ItemTouchHelper.Callback() {

    private var iconLeft = ContextCompat.getDrawable(context, R.drawable.ic_bucket)
    private var backgroundLeft = ContextCompat.getDrawable(context, R.drawable.bg_items_list_delete)

    override fun isItemViewSwipeEnabled() = true

    override fun isLongPressDragEnabled() = false

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: ViewHolder): Int {
        return makeMovementFlags(IntConstants.ZERO, ItemTouchHelper.START)
    }

    override fun onMove(
        recyclerView: RecyclerView, viewHolder: ViewHolder, target: ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
        callback.onSwipedAway(viewHolder.adapterPosition)
    }

    override fun onChildDraw(
        c: Canvas, recyclerView: RecyclerView, viewHolder: ViewHolder,
        dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        val itemView = viewHolder.itemView
        val backgroundCornerOffset = 0

        val mdDx = abs(dX)

        if (dX < 0) {
            val leftIntrinsicWidth = iconLeft?.intrinsicWidth ?: IntConstants.ZERO
            val leftIntrinsicHeight = iconLeft?.intrinsicHeight ?: IntConstants.ZERO
            val iconMargin = if (mdDx > leftIntrinsicWidth + backgroundCornerOffset)
                ((abs(dX) - leftIntrinsicWidth - backgroundCornerOffset) / 2).toInt()
            else
                leftIntrinsicWidth + backgroundCornerOffset

            val normal = mdDx > leftIntrinsicWidth + backgroundCornerOffset * 3

            val iconRight = if (normal)
                itemView.right - iconMargin
            else {
                itemView.right - backgroundCornerOffset
            }

            val iconLeft = if (normal)
                itemView.right - iconMargin - leftIntrinsicWidth
            else {
                itemView.right + dX.toInt() + backgroundCornerOffset * 2
            }

            val iconTop = if (normal)
                itemView.top + (itemView.height - leftIntrinsicHeight) / 2
            else
                itemView.top + (itemView.height - (iconRight - iconLeft)) / 2
            val iconBottom = if (normal)
                iconTop + leftIntrinsicHeight
            else
                itemView.top + (itemView.height + (iconRight - iconLeft)) / 2

            this.iconLeft?.setBounds(iconLeft, iconTop, iconRight, iconBottom)

            backgroundLeft?.setBounds(
                itemView.right + dX.toInt() + backgroundCornerOffset,
                itemView.top+1.dpToPx(), itemView.right, itemView.bottom - 1.dpToPx()
            )
        } else {
            backgroundLeft?.setBounds(0, 0, 0, 0)
            iconLeft?.setBounds(0, 0, 0, 0)
        }
        backgroundLeft?.draw(c)
        iconLeft?.draw(c)

    }

    interface Callback {
        fun onSwipedAway(position: Int)
    }
}