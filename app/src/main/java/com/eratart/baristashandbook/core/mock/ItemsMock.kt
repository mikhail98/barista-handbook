package com.eratart.baristashandbook.core.mock

import com.eratart.baristashandbook.core.util.ImageUrlUtil
import com.eratart.baristashandbook.domain.model.Item

object ItemsMock {

    private fun getItem(pos: Int, categoryTitle: String) = Item(
        "espresso$pos",
        "Латте $categoryTitle $pos",
        "Латте описание $pos",
        listOf(ImageUrlUtil.getImageUrl(ImageUrlUtil.DRINKS, "latte", "Frame%201.jpg")),
        IngredientsMock.getIngredients(5),
        listOf("Сначала укропу", "Потом кошачью жопу"),
        "cup",
        pos
    )

    fun getItems(amount: Int, categoryTitle: String): MutableList<Item> {
        val items = mutableListOf<Item>()
        for (i in 0 until amount) {
            items.add(getItem(i, categoryTitle))
        }
        return items
    }
}