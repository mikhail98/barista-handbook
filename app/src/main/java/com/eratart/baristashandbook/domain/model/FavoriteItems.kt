package com.eratart.baristashandbook.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoriteItems(
    val items: List<Item> = listOf()
) : Parcelable