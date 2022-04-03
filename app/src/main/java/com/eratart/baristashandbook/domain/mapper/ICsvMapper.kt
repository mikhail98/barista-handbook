package com.eratart.baristashandbook.domain.mapper

import com.eratart.baristashandbook.core.constants.StringConstants.SLASH
import com.eratart.baristashandbook.core.constants.StringConstants.SLASH_UNICODE

interface ICsvMapper<T> {

    companion object {
        fun getImageUrl(path: String): String {
            return "https://firebasestorage.googleapis.com/v0/b/barista-s-handbook.appspot.com/o/"
                .plus(path.replace(SLASH, SLASH_UNICODE))
                .plus("?alt=media")
        }
    }

    fun mapFromCsvLine(inputList: List<Array<String>>): List<T>
}