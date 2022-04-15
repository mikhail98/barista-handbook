package com.eratart.baristashandbook.data.mapper.repo

import com.eratart.baristashandbook.core.constants.IntConstants
import com.eratart.baristashandbook.core.constants.IntConstants.ZERO
import com.eratart.baristashandbook.core.constants.StringConstants.EMPTY
import com.eratart.baristashandbook.core.constants.StringConstants.NEW_LINE
import com.eratart.baristashandbook.data.mapper.ICsvMapper
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.model.ItemCategory

class ItemsCategoriesMapper : ICsvMapper<ItemCategory> {

    companion object {
        private const val MIN_LINE_SIZE = 2
    }

    override fun mapFromCsvLine(inputList: List<Array<String>>): List<ItemCategory> {
        if (inputList.size <= IntConstants.ONE) return emptyList()
        val newList = mutableListOf<ItemCategory>()
        inputList.drop(IntConstants.ONE).forEach { itemCategory ->
            if (itemCategory.size == MIN_LINE_SIZE) {
                val id = itemCategory[0]
                if (id.isNotEmpty()) {
                    val title = itemCategory[1]
                    val item = ItemCategory(id, title, listOf())
                    newList.add(item)
                }
            }
        }
        return newList
    }
}