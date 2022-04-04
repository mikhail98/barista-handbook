package com.eratart.baristashandbook.core.util

import com.eratart.baristashandbook.core.constants.StringConstants

object ImageUrlUtil {

    const val DRINKS = "drinks"
    const val DISHES = "dishes"

    fun getImageUrl(type: String, id: String, path: String): String {
        return "https://firebasestorage.googleapis.com/v0/b/barista-s-handbook.appspot.com/o/"
            .plus(type).plus( StringConstants.SLASH_UNICODE)
            .plus(id).plus( StringConstants.SLASH_UNICODE)
            .plus(path.replace(StringConstants.SLASH, StringConstants.SLASH_UNICODE))
            .plus("?alt=media")
    }
}