package com.eratart.baristashandbook.core.util

import com.eratart.baristashandbook.core.constants.StringConstants

object ImageUrlUtil {

    private const val BASE_IMAGE_URL =
        "https://firebasestorage.googleapis.com/v0/b/barista-s-handbook.appspot.com/o/"

    const val DRINKS = "drinks"
    const val DISHES = "cups"

    fun getImageUrl(type: String, id: String, path: String): String {
        return BASE_IMAGE_URL
            .plus(type).plus(StringConstants.SLASH_UNICODE)
            .plus(id).plus(StringConstants.SLASH_UNICODE)
            .plus(path.replace(StringConstants.SLASH, StringConstants.SLASH_UNICODE))
            .plus("?alt=media")
    }

    fun getDishesImageUrl(type: String, id: String, path: String): String {
        return BASE_IMAGE_URL
            .plus(type).plus(StringConstants.SLASH_UNICODE)
            .plus(id).plus(StringConstants.SLASH_UNICODE)
            .plus(path.replace(StringConstants.SLASH, StringConstants.SLASH_UNICODE))
            .plus("?alt=media")
    }
}