package com.eratart.baristashandbook.data.repository

import com.eratart.baristashandbook.domain.model.Item
import com.eratart.baristashandbook.domain.repository.IItemsRepo

class ItemsRepo : IItemsRepo {
    override fun getItems(): List<Item> {
        return emptyList()
    }
}