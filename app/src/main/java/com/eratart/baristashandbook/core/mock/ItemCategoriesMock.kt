package com.eratart.baristashandbook.core.mock

import com.eratart.baristashandbook.domain.model.ItemCategory

object ItemCategoriesMock {

    private fun getCategory(pos: Int): ItemCategory {
        val title = "Черный кофе - $pos"
        val drinks = ItemsMock.getItems(7, title)
        return ItemCategory("category$pos", title, drinks.size, drinks)
    }

    fun getCategories(amount: Int = 4): MutableList<ItemCategory> {
        val categories = mutableListOf<ItemCategory>()
        for (i in 0 until amount) {
            categories.add(getCategory(i))
        }
        return categories
    }
}