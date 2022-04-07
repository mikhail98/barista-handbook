package com.eratart.baristashandbook.baseui.view.other


import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import com.eratart.baristashandbook.R
import com.eratart.baristashandbook.core.ext.loadImageWithGlide

class FavoritesButton(context: Context, attributeSet: AttributeSet? = null) :
    FrameLayout(context, attributeSet) {

    private val view = inflate(context, R.layout.view_favorites_button, this)

    private val ivFavorite: ImageView by lazy { view.findViewById(R.id.ivFavorite) }

    private val attributes by lazy {
        context.theme.obtainStyledAttributes(attributeSet, R.styleable.FavoritesButton, 0, 0)
    }
    var isChecked: Boolean = false

    private var onCheckChange: ((Boolean) -> Unit)? = null
    fun setOnCheckChangeListener(listener: ((Boolean) -> Unit)?) {
        this.onCheckChange = listener
    }

    init {
        try {
            isChecked = attributes.getBoolean(R.styleable.FavoritesButton_isFavorite, false)
        } finally {
            attributes.recycle()
        }
        updateIsFavorite(isChecked)
        initListener()
    }

    fun setFavorite(isFavorite: Boolean, triggerListener: Boolean) {
        updateIsFavorite(isFavorite)
        if (triggerListener) {
            onCheckChange?.invoke(isChecked)
        }
    }

    private fun updateIsFavorite(isFavorite: Boolean) {
        isChecked = isFavorite
        val res = if (isChecked) {
            R.drawable.ic_item_favorites
        } else {
            R.drawable.ic_item_favorites_outline
        }
        ivFavorite.loadImageWithGlide(res)
    }

    private fun initListener() {
        ivFavorite.setOnClickListener {
            setFavorite(!isChecked, true)
        }
    }

    fun isFavorite(): Boolean = isChecked
}