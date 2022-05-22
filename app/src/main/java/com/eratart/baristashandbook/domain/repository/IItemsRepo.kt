package com.eratart.baristashandbook.domain.repository

import com.eratart.baristashandbook.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface IItemsRepo {
    fun getItems(): Flow<List<Item>>
}