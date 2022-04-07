package com.eratart.baristashandbook.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ingredient(
    val title: String,
    val volume: String
): Parcelable