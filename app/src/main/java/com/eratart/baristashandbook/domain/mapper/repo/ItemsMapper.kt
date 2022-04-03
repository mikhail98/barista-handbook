package com.eratart.baristashandbook.domain.mapper.repo

import com.eratart.baristashandbook.core.constants.IntConstants
import com.eratart.baristashandbook.core.constants.StringConstants
import com.eratart.baristashandbook.domain.mapper.ICsvMapper
import com.eratart.baristashandbook.domain.model.Item

class ItemsMapper : ICsvMapper<Item> {

    companion object {
        private const val MIN_LINE_SIZE = 7
    }

    override fun mapFromCsvLine(inputList: List<Array<String>>): List<Item> {
        if (inputList.size <= IntConstants.ONE) return emptyList()
        val newList = mutableListOf<Item>()
        inputList.drop(IntConstants.ONE).forEach { drink ->
            if (drink.size == MIN_LINE_SIZE) {
                val item = Item(
                    drink[0],
                    drink[1],
                    drink[2],
                    drink[3].split(StringConstants.NEW_LINE),
                    drink[4].split(StringConstants.NEW_LINE),
                    drink[5],
                    drink[6].toIntOrNull() ?: IntConstants.ONE
                )
                newList.add(item)
            }
        }
        return newList
    }
}