package com.eratart.baristashandbook.core.util

import android.content.Context
import com.eratart.baristashandbook.core.constants.IntConstants
import java.util.*

object LocaleUtil {

    const val DEFAULT_LANGUAGE = "ru"

    fun getLocale(context: Context): Locale {
        return context.resources.configuration.locales.get(IntConstants.ZERO)
    }

    fun getLanguage(context: Context): String {
        return getLocale(context).language
    }
}