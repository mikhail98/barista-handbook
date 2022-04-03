package com.eratart.baristashandbook.data.repository

import android.content.Context
import com.eratart.baristashandbook.domain.mapper.repo.ItemsMapper
import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.repository.IItemsRepo

class ItemsRepo(context: Context) : BaseRepo(context), IItemsRepo {

    companion object {
        private const val TABLE_PREFIX = "coffee_drinks_items_"
    }

    private val itemsMapper by lazy { ItemsMapper() }

    override fun getItems(): List<Item> {
        return getDataFromTable(TABLE_PREFIX, itemsMapper)
    }
}