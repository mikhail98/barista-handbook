package com.eratart.baristashandbook.core.mock

import com.eratart.baristashandbook.domain.model.Dish

object DishesMock {

    private const val PHOTO = "https://sc04.alicdn.com/kf/HTB14xZsaJzvK1RkSnfoq6zMwVXaK.jpg"

    fun getDish(pos: Int) = Dish(
        "cup",
        "Чашка-кружка $pos",
        "Сейчас я вам покажу, откуда на Беларусь готовилось нападение",
        photos = listOf(PHOTO, PHOTO),
        "228 cl"
    )

    fun getDishes(amount: Int): MutableList<Dish> {
        val items = mutableListOf<Dish>()
        for (i in 0 until amount) {
            items.add(getDish(i))
        }
        return items
    }
}