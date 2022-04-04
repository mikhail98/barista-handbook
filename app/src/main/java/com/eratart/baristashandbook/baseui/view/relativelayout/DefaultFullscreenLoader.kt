package com.eratart.baristashandbook.baseui.view.relativelayout

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.eratart.baristashandbook.R

class DefaultFullscreenLoader(context: Context, attributeSet: AttributeSet? = null) :
    RelativeLayout(context, attributeSet) {

    init {
        inflate(getContext(), R.layout.view_fullscreen_loader, this)
    }
}