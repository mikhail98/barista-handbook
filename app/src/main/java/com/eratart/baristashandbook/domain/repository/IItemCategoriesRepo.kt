package com.eratart.baristashandbook.domain.repository

import com.eratart.baristashandbook.domain.model.ItemCategory
import kotlinx.coroutines.flow.Flow

interface IItemCategoriesRepo {
    fun getItemCategories(): Flow<List<ItemCategory>>
}