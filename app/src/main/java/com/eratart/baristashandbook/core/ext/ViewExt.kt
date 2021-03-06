package com.eratart.baristashandbook.core.ext

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.eratart.baristashandbook.baseui.view.viewpager.ViewPageScroller
import com.eratart.baristashandbook.core.constants.FloatConstants
import com.eratart.baristashandbook.core.ext.ViewExt.DURATION_DEFAULT_GONE
import com.eratart.baristashandbook.core.ext.ViewExt.DURATION_DEFAULT_VISIBLE
import java.lang.reflect.Field

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

fun ViewPager2.setViewPageScroller(viewPageScroller: ViewPageScroller) {
    try {
        val mScroller: Field = ViewPager::class.java.getDeclaredField("mScroller")
        mScroller.isAccessible = true
        mScroller.set(this, viewPageScroller)
    } catch (e: Exception) {
        e.printError()
    }
}

fun TextView.setTextAnimation(
    textRes: Int, duration: Long = 300, completion: (() -> Unit)? = null
) {
    val newText = context.getString(textRes)
    if (this.text == newText) return
    fadeOutAnimation(duration) {
        this.text = newText
        fadeInAnimation(duration) {
            completion?.invoke()
        }
    }
}

fun View.fadeOutAnimation(
    duration: Long = 150,
    visibility: Int = View.INVISIBLE,
    completion: (() -> Unit)? = null
) {
    animate()
        .alpha(0f)
        .setDuration(duration)
        .withEndAction {
            this.visibility = visibility
            completion?.let {
                it()
            }
        }
}

fun View.fadeInAnimation(duration: Long = 150, completion: (() -> Unit)? = null) {
    alpha = 0f
    visibility = View.VISIBLE
    animate()
        .alpha(1f)
        .setDuration(duration)
        .withEndAction {
            completion?.let {
                it()
            }
        }
}

fun Int.pxToDp(): Float {
    return this.toFloat() / Resources.getSystem().displayMetrics.density
}

fun Float.pxToDp(): Float {
    return this / Resources.getSystem().displayMetrics.density
}

fun Int.dpToPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}

fun Float.dpToPx(): Float {
    return this * Resources.getSystem().displayMetrics.density
}

fun ImageView.loadImageWithGlide(url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
}

fun ImageView.loadImageWithGlide(drawable: Drawable?) {
    Glide.with(context)
        .load(drawable)
        .into(this)
}

fun ImageView.loadImageWithGlide(@DrawableRes drawableRes: Int) {
    Glide.with(context)
        .load(drawableRes)
        .into(this)
}


fun ImageView.loadImageWithGlideCircle(url: String) {
    Glide.with(context)
        .load(url)
        .circleCrop()
        .into(this)
}

fun ImageView.loadImageWithGlideCircle(drawable: Drawable?) {
    Glide.with(context)
        .load(drawable)
        .circleCrop()
        .into(this)
}

fun ImageView.loadImageWithGlideCircle(@DrawableRes drawableRes: Int) {
    Glide.with(context)
        .load(drawableRes)
        .circleCrop()
        .into(this)
}

fun Context.getBitmapFromUrl(url: String, onBitmapReady: (Bitmap?) -> Unit) {
    Glide.with(this)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                onBitmapReady.invoke(resource)
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                super.onLoadFailed(errorDrawable)
                onBitmapReady.invoke(null)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })
}

fun View.setHeight(height: Float) {
    setHeight(height.toInt())
}

fun View.setHeight(height: Int) {
    val viewParams = this.layoutParams as ConstraintLayout.LayoutParams
    viewParams.height = height
    this.layoutParams = viewParams
}
