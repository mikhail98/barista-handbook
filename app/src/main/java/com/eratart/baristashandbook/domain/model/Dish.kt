package com.eratart.baristashandbook.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dish(
    val id: String,
    val title: String,
    val description: String,
    val photos: List<String>,
    val volume: String
) : Parcelable