package com.eratart.baristashandbook.domain.model

data class Dish(
    val id: String,
    val title: String,
    val description: String,
    val photos: List<String>
)