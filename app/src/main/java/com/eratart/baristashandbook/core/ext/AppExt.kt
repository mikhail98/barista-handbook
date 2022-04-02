package com.eratart.baristashandbook.core.ext

import android.os.Handler
import android.os.Looper

fun postDelayed(time: Long, listener: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        listener.invoke()
    }, time)
}