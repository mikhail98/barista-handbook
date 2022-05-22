package com.eratart.baristashandbook.data.mapper.repo

import com.eratart.baristashandbook.core.constants.IntConstants
import com.eratart.baristashandbook.core.constants.StringConstants
import com.eratart.baristashandbook.core.util.ImageUrlUtil
import com.eratart.baristashandbook.data.mapper.BaseFirebaseDBMapper
import com.eratart.baristashandbook.domain.model.Ingredient
import com.eratart.baristashandbook.domain.model.Item

class ItemsFirebaseDBMapper : BaseFirebaseDBMapper<Item>() {

    companion object {
        private const val INGREDIENTS_SPLITTER = "::"
    }

    override fun mapFromData(inputList: List<HashMap<String, String>>): List<Item> {
        if (inputList.isEmpty()) return emptyList()
        val newList = mutableListOf<Item>()
        inputList.forEach { drink ->
            val idParam = drink["id"] ?: return@forEach

            val titleParam = drink["title"] ?: return@forEach

            val descriptionParam = drink["description"] ?: return@forEach

            val photosParam = drink["photos"] ?: return@forEach
            val photos = if (photosParam.isNotEmpty()) {
                photosParam.split(StringConstants.NEW_LINE).map { path ->
                    ImageUrlUtil.getImageUrl(ImageUrlUtil.DRINKS, idParam, path)
                }
            } else {
                listOf()
            }

            val ingredientsParam = drink["ingredients"] ?: return@forEach
            val ingredients = ingredientsParam.split(StringConstants.NEW_LINE).map { ingredient ->
                val ingredientList = ingredient.split(INGREDIENTS_SPLITTER)
                val title = ingredientList.first()
                val volume = if (ingredientList.size == IntConstants.TWO) {
                    ingredientList.last()
                } else {
                    StringConstants.EMPTY
                }
                Ingredient(title, volume)
            }
            val instructionsParam = drink["instructions"] ?: return@forEach
            val instructions = instructionsParam.split(StringConstants.NEW_LINE)

            val dishParam = drink["dish_id"] ?: return@forEach

            val portionsParam = drink["portions"] ?: return@forEach
            val portionsAmount = portionsParam.toIntOrNull() ?: IntConstants.ONE

            val categoriesParam = drink["category_id"] ?: return@forEach
            val categoriesIdList = categoriesParam.split(StringConstants.NEW_LINE)

            val item = Item(
                idParam, titleParam, descriptionParam, photos, ingredients,
                instructions, dishParam, portionsAmount, categoriesIdList
            )
            newList.add(item)

        }
        return newList
    }
}