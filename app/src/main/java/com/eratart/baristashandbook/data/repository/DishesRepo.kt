package com.eratart.baristashandbook.data.repository

import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.repository.IDishesRepo

class DishesRepo : IDishesRepo {
    override fun getDishes(): List<Dish> {
        return emptyList()
    }
}