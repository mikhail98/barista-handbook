package com.eratart.baristashandbook.baseui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout

class NonClickableRelativeLayout(context: Context, attrs: AttributeSet) :
    RelativeLayout(context, attrs) {

    init {
        setOnClickListener { }
    }
}