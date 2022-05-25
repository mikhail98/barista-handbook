package com.eratart.baristashandbook.data.mapper.repo

import com.eratart.baristashandbook.data.mapper.BaseFirebaseDBMapper
import com.eratart.baristashandbook.domain.model.ItemCategory

class ItemsCategoriesDBMapper : BaseFirebaseDBMapper<ItemCategory>() {

    override fun mapFromData(inputList: List<HashMap<String, String>>): List<ItemCategory> {
        if (inputList.isEmpty()) return emptyList()
        val newList = mutableListOf<ItemCategory>()
        inputList.forEach { itemCategory ->
            val idParam = itemCategory.getParam("id") ?: return@forEach
            val titleParam = itemCategory.getParam("title") ?: return@forEach
            val category = ItemCategory(idParam, titleParam, listOf())
            newList.add(category)
        }
        return newList
    }
}