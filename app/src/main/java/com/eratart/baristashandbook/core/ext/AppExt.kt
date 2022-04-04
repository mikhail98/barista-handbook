package com.eratart.baristashandbook.core.ext

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.view.WindowManager

fun postDelayed(time: Long, listener: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        listener.invoke()
    }, time)
}

fun Context.getWindowManager() = getSystemService(Context.WINDOW_SERVICE) as WindowManager

fun Context.getScreenWidth(): Int {
    val displayMetrics = DisplayMetrics()
    getWindowManager().defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.widthPixels
}