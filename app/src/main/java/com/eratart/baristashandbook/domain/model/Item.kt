package com.eratart.baristashandbook.domain.model

data class Item(
    val id: String,
    val title: String,
    val description: String,
    val photos: List<String>,
    val ingredients: List<Ingredient>,
    val dish: String,
    val portionsAmount: Int
)