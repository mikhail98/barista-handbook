package com.eratart.baristashandbook.data.repository

import android.content.Context
import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.repository.IDishesRepo

class DishesRepo(context: Context) : BaseRepo(context), IDishesRepo {
    override fun getDishes(): List<Dish> {
        return emptyList()
    }
}