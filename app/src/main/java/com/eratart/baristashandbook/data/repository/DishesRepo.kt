package com.eratart.baristashandbook.data.repository

import android.content.Context
import com.eratart.baristashandbook.data.mapper.repo.DishesMapper
import com.eratart.baristashandbook.domain.model.Dish
import com.eratart.baristashandbook.domain.repository.IDishesRepo

class DishesRepo(context: Context) : BaseRepo(context), IDishesRepo {

    companion object {
        private const val TABLE_PREFIX = "dishes_items_"
    }

    private val dishesMapper by lazy { DishesMapper() }

    override fun getDishes(): List<Dish> {
        return getDataFromTable(TABLE_PREFIX, dishesMapper)
    }
}