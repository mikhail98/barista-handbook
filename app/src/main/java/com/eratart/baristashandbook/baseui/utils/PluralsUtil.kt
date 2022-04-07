package com.eratart.baristashandbook.baseui.utils

import android.content.Context
import androidx.annotation.StringRes

object PluralsUtil {
    fun getQuantityString(
        context: Context,
        number: Int,
        @StringRes one: Int,
        @StringRes two: Int,
        @StringRes five: Int,
        @StringRes zero: Int = 0,
        formatNumber: String? = null
    ): String {
        if (zero != 0 && number == 0) {
            return context.getString(zero)
        }
        return if ((number - number % 10) % 100 != 10) {
            when {
                number % 10 == 1 -> {
                    context.getString(one, number)
                }
                number % 10 in 2..4 -> {
                    context.getString(two, number)
                }
                else -> {
                    context.getString(five, number)
                }
            }
        } else {
            context.getString(five, formatNumber ?: number)
        }
    }
}