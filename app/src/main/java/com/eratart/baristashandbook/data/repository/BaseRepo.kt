package com.eratart.baristashandbook.data.repository

import android.content.Context
import com.eratart.baristashandbook.core.constants.StringConstants
import com.eratart.baristashandbook.core.util.LocaleUtil
import com.eratart.baristashandbook.data.mapper.ICsvMapper
import com.opencsv.CSVReader
import java.io.InputStreamReader

abstract class BaseRepo(private val context: Context) {

    companion object {
        private fun getTableName(tablePrefix: String, locale: String): String {
            return tablePrefix.plus(locale).plus(StringConstants.CSV_EXT)
        }
    }

    fun <T> getDataFromTable(tablePrefix: String, mapper: ICsvMapper<T>): List<T> {
        val list = mutableListOf<T>()

        val inputStream = try {
            context.assets.open(getTableName(tablePrefix, LocaleUtil.getLanguage(context)))
        } catch (e: Exception) {
            context.assets.open(getTableName(tablePrefix, LocaleUtil.DEFAULT_LANGUAGE))
        }

        val reader = CSVReader(InputStreamReader(inputStream))
        val itemsList = mapper.mapFromCsvLine(reader.readAll())
        list.addAll(itemsList)

        return list
    }
}