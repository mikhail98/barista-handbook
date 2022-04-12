package com.eratart.baristashandbook.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val id: String,
    val title: String,
    val description: String,
    val photos: List<String>,
    val ingredients: List<Ingredient>,
    val instructions: List<String>,
    val dishId: String,
    val portionsAmount: Int,
    val categoryId: String
) : Parcelable