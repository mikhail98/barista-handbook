package com.eratart.baristashandbook.core.constants

object IntConstants {
    const val ZERO = 0
    const val ONE = 1
    const val TWO = 2
    const val THREE = 3
    const val MINUS_ONE = -1
    const val MINUS_TWO = -2
    const val MINUS_THREE = -3
}

fun Int?.orZero() = this ?: IntConstants.ZERO