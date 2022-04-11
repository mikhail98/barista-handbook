package com.eratart.baristashandbook.data.model

import com.google.gson.annotations.SerializedName

class NewsBotResponse(
    @SerializedName("title")
    val title: String?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("photoUrl")
    val photoUrl: String?,
    @SerializedName("url")
    val url: String?
)