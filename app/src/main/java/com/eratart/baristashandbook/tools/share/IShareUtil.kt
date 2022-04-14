package com.eratart.baristashandbook.tools.share

import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.model.Item

interface IShareUtil {

    fun shareDishAsText(dish: Dish)

    fun shareItemAsText(item: Item)
}