package com.eratart.baristashandbook.data.mapper.repo

import com.eratart.baristashandbook.core.constants.StringConstants
import com.eratart.baristashandbook.core.util.ImageUrlUtil
import com.eratart.baristashandbook.data.mapper.BaseFirebaseDBMapper
import com.eratart.baristashandbook.domain.model.Dish

class DishesDBMapper : BaseFirebaseDBMapper<Dish>() {

    override fun mapFromData(inputList: List<HashMap<String, String>>): List<Dish> {
        if (inputList.isEmpty()) return emptyList()
        val newList = mutableListOf<Dish>()
        inputList.forEach { dish ->
            val idParam = dish.getParam("id") ?: return@forEach
            val titleParam = dish.getParam("title") ?: return@forEach
            val descriptionParam = dish.getParam("description") ?: return@forEach

            val photosParam = dish.getParam("photos") ?: return@forEach
            val photos = if (photosParam.isNotEmpty()) {
                photosParam.split(StringConstants.NEW_LINE).map { path ->
                    ImageUrlUtil.getDishesImageUrl(ImageUrlUtil.DISHES, idParam, path)
                }
            } else {
                listOf()
            }
            val volumeParam = dish.getParam("volume") ?: return@forEach

            val itemCategory = Dish(idParam, titleParam, descriptionParam, photos, volumeParam)
            newList.add(itemCategory)

        }
        return newList
    }
}