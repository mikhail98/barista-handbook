package com.eratart.baristashandbook.domain.repository

import com.eratart.baristashandbook.domain.model.Dish
import kotlinx.coroutines.flow.Flow

interface IDishesRepo {
    fun getDishes(): Flow<List<Dish>>
}