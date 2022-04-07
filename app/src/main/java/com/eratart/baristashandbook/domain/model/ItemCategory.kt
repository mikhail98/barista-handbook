package com.eratart.baristashandbook.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemCategory(
    val id: String,
    val title: String,
    val count: Int,
    val drinks: List<Item>
) : Parcelable