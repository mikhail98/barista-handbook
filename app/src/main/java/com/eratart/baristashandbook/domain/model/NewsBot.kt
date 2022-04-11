package com.eratart.baristashandbook.domain.model

import android.os.Parcelable
import com.eratart.baristashandbook.core.constants.StringConstants
import com.eratart.baristashandbook.core.ext.getDateTime
import kotlinx.parcelize.Parcelize

@Parcelize
class NewsBot(
    val id: Long,
    val date: Long,
    val title: String,
    val text: String?,
    val photoUrl: String?,
    val url: String?
) : Parcelable {

    fun getTextToShare(): String {
        var textToShare = title.plus(StringConstants.NEW_LINE).plus(date.getDateTime())

        if (text != null) {
            textToShare += StringConstants.NEW_LINE
            textToShare += StringConstants.NEW_LINE
            textToShare += text
        }

        if (url != null) {
            textToShare += StringConstants.NEW_LINE
            textToShare += StringConstants.NEW_LINE
            textToShare += url
        }

        return textToShare
    }
}