package com.eratart.baristashandbook.core.ext

import com.eratart.baristashandbook.BuildConfig

fun Throwable.printError(isPrint: Boolean = BuildConfig.DEBUG) {
    if (isPrint) {
        println("RE:: ${this.localizedMessage ?: this.javaClass.name}")
        this.printStackTrace()
    }
}