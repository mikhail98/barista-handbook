package com.eratart.baristashandbook.core.mock

import com.eratart.baristashandbook.core.util.ImageUrlUtil
import com.eratart.baristashandbook.domain.model.Ingredient
import com.eratart.baristashandbook.domain.model.Item

object ItemsMock {

    private fun getItem(pos: Int, categoryTitle: String) = Item(
        "latte",
        "Латте $categoryTitle $pos",
        "Латте описание $pos",
        listOf(ImageUrlUtil.getImageUrl(ImageUrlUtil.DRINKS, "latte", "Frame%201.jpg")),
        listOf(Ingredient("Пакетика травы", "2")),
        "cup",
        1
    )

    fun getItems(amount: Int, categoryTitle: String): MutableList<Item> {
        val items = mutableListOf<Item>()
        for (i in 0 until amount) {
            items.add(getItem(i, categoryTitle))
        }
        return items
    }
}