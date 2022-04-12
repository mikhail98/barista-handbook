package com.eratart.baristashandbook.domain.repository

import com.eratart.baristashandbook.domain.model.ItemCategory

interface IItemCategoriesRepo {
    fun getItemCategories(): List<ItemCategory>
}