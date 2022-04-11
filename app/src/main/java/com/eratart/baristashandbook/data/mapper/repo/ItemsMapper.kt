package com.eratart.baristashandbook.data.mapper.repo

import com.eratart.baristashandbook.core.constants.IntConstants
import com.eratart.baristashandbook.core.constants.StringConstants
import com.eratart.baristashandbook.core.util.ImageUrlUtil
import com.eratart.baristashandbook.data.mapper.ICsvMapper
import com.eratart.baristashandbook.domain.model.Ingredient
import com.eratart.baristashandbook.domain.model.Item

class ItemsMapper : ICsvMapper<Item> {

    companion object {
        private const val INGREDIENTS_SPLITTER = "::"
        private const val MIN_LINE_SIZE = 8
    }

    override fun mapFromCsvLine(inputList: List<Array<String>>): List<Item> {
        if (inputList.size <= IntConstants.ONE) return emptyList()
        val newList = mutableListOf<Item>()
        inputList.drop(IntConstants.ONE).forEach { drink ->
            if (drink.size == MIN_LINE_SIZE) {
                val id = drink[0]
                val photos = drink[3].split(StringConstants.NEW_LINE).map { path ->
                    ImageUrlUtil.getImageUrl(ImageUrlUtil.DRINKS, id, path)
                }
                val ingredients = drink[4].split(StringConstants.NEW_LINE).map { ingredient ->
                    val ingredientList = ingredient.split(INGREDIENTS_SPLITTER)
                    val title = ingredientList.first()
                    val volume = if (ingredientList.size == IntConstants.TWO) {
                        ingredientList.last()
                    } else {
                        StringConstants.EMPTY
                    }
                    Ingredient(title, volume)
                }
                val instructions = drink[5].split(StringConstants.NEW_LINE)
                val portionsAmount = drink[7].toIntOrNull() ?: IntConstants.ONE
                val item = Item(
                    id, drink[1], drink[2], photos, ingredients,
                    instructions, drink[6], portionsAmount
                )
                newList.add(item)
            }
        }
        return newList
    }
}