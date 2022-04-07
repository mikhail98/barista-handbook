package com.eratart.baristashandbook.baseui.view.other


import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import com.eratart.baristashandbook.R
import com.google.android.material.textview.MaterialTextView

class MenuItem(context: Context, attributeSet: AttributeSet? = null) :
    FrameLayout(context, attributeSet) {

    private val view = inflate(context, R.layout.view_menu, this)

    private val ivMenuItem: ImageView by lazy { view.findViewById(R.id.ivMenuItem) }
    private val tvMenuItem: MaterialTextView by lazy { view.findViewById(R.id.tvMenuItem) }

    private val attributes by lazy {
        context.theme.obtainStyledAttributes(attributeSet, R.styleable.MenuItem, 0, 0)
    }
    var text: String? = null
    var icon: Drawable? = null

    init {
        try {
            text = attributes.getString(R.styleable.MenuItem_text)
            icon = attributes.getDrawable(R.styleable.MenuItem_icon)
        } finally {
            attributes.recycle()
        }

        if (!text.isNullOrEmpty()) {
            tvMenuItem.text = text
        }

        if (icon != null) {
            ivMenuItem.setImageDrawable(icon)
        }
    }
}