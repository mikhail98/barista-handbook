package com.eratart.baristashandbook.core.ext

import android.os.Handler
import android.os.Looper
import android.view.View
import com.eratart.baristashandbook.core.constants.FloatConstants
import com.eratart.baristashandbook.core.ext.ViewExt.DURATION_DEFAULT_GONE
import com.eratart.baristashandbook.core.ext.ViewExt.DURATION_DEFAULT_VISIBLE

object ViewExt {
    const val DURATION_DEFAULT_GONE = 150L
    const val DURATION_DEFAULT_VISIBLE = 300L
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.setVisibleAlpha(flag: Boolean) {
    if (flag) visibleWithAlpha() else goneWithAlpha()
}

fun View.visibleWithAlpha(duration: Long = DURATION_DEFAULT_VISIBLE) {
    if (this.visibility != View.VISIBLE) {
        this.alpha = FloatConstants.ZERO
        this.visible()
        this.animate().alpha(FloatConstants.ONE).setDuration(duration).start()
    }
}

fun View.goneWithAlpha(duration: Long = DURATION_DEFAULT_GONE) {
    if (this.visibility == View.VISIBLE) {
        this.alpha = FloatConstants.ONE
        this.animate().alpha(FloatConstants.ZERO).setDuration(duration).start()
        Handler(Looper.getMainLooper()).postDelayed({ this.gone() }, duration)
    }
}
