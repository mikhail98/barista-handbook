package com.eratart.baristashandbook.core.mock

import com.eratart.baristashandbook.domain.model.Ingredient

object IngredientsMock {

    fun getIngredient(pos: Int) = Ingredient(
        "Ингредиент один", "$pos мл"
    )

    fun getIngredients(amount: Int): MutableList<Ingredient> {
        val items = mutableListOf<Ingredient>()
        for (i in 0 until amount) {
            items.add(getIngredient(i))
        }
        return items
    }
}