package com.eratart.baristashandbook.domain.repository

import com.eratart.baristashandbook.domain.model.Dish

interface IDishesRepo {
    fun getDishes(): List<Dish>
}