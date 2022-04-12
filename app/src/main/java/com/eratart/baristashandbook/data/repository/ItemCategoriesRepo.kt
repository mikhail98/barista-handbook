package com.eratart.baristashandbook.data.repository

import android.content.Context
import com.eratart.baristashandbook.core.constants.StringConstants
import com.eratart.baristashandbook.data.mapper.repo.ItemsCategoriesMapper
import com.eratart.baristashandbook.domain.model.ItemCategory
import com.eratart.baristashandbook.domain.repository.IItemCategoriesRepo

class ItemCategoriesRepo(context: Context) : BaseRepo(context), IItemCategoriesRepo {

    companion object {
        private const val TABLE_PREFIX = "categories".plus(StringConstants.UNDERLINE)
    }

    private val categoriesMapper by lazy { ItemsCategoriesMapper() }

    override fun getItemCategories(): List<ItemCategory> {
        return getDataFromTable(TABLE_PREFIX, categoriesMapper)
    }
}