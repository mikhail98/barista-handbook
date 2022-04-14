package com.eratart.baristashandbook.data.mapper.repo

import com.eratart.baristashandbook.core.constants.IntConstants
import com.eratart.baristashandbook.core.constants.StringConstants
import com.eratart.baristashandbook.core.util.ImageUrlUtil
import com.eratart.baristashandbook.data.mapper.ICsvMapper
import com.eratart.baristashandbook.domain.model.Dish

class DishesMapper : ICsvMapper<Dish> {

    companion object {
        private const val MIN_LINE_SIZE = 5
    }

    override fun mapFromCsvLine(inputList: List<Array<String>>): List<Dish> {
        if (inputList.size <= IntConstants.ONE) return emptyList()
        val newList = mutableListOf<Dish>()
        inputList.drop(IntConstants.ONE).forEach { dish ->
            if (dish.size == MIN_LINE_SIZE) {
                val id = dish[0]
                val title = dish[1]
                val description = dish[2]
                val photos = if (dish[3].isNotEmpty()) {
                    dish[3].split(StringConstants.NEW_LINE).map { path ->
                        ImageUrlUtil.getDishesImageUrl(ImageUrlUtil.DISHES, id, path)
                    }
                } else {
                    listOf()
                }
                val volume = dish[4]
                val item = Dish(id, title, description, photos, volume)
                newList.add(item)
            }
        }
        return newList
    }
}