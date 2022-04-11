package com.eratart.baristashandbook.core.ext

import com.eratart.baristashandbook.core.constants.FormatConstants
import java.util.*

fun initCalendar(timeInMillis: Long = System.currentTimeMillis()): Calendar {
    return Calendar.getInstance().apply {
        this.timeInMillis = timeInMillis
    }
}

fun Calendar.getDateTime(): String {
    val hours = get(Calendar.HOUR_OF_DAY)
    val minute = get(Calendar.MINUTE)

    val day = get(Calendar.DAY_OF_MONTH)
    val month = get(Calendar.MONTH)
    val year = get(Calendar.YEAR)

    return String.format(FormatConstants.TIME_FORMAT, day, month, year, hours, minute)
}

fun Long.getDateTime(): String {
    return initCalendar(this).getDateTime()
}