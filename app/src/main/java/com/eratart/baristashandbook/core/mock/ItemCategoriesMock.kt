package com.eratart.baristashandbook.core.mock

import com.eratart.baristashandbook.domain.model.ItemCategory

object ItemCategoriesMock {

    private fun getCategory(pos: Int) = ItemCategory("category$pos", "Черный кофе - $pos", pos)

    fun getCategories(amount: Int): MutableList<ItemCategory> {
        val categories = mutableListOf<ItemCategory>()
        for (i in 0 until amount) {
            categories.add(getCategory(i))
        }
        return categories
    }
}