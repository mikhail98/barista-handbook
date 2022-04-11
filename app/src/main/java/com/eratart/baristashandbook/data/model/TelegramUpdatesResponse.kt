package com.eratart.baristashandbook.data.model

import com.google.gson.annotations.SerializedName

data class TelegramUpdatesResponse(
    @SerializedName("ok")
    val ok: Boolean?,
    @SerializedName("result")
    val results: List<TelegramUpdateResultResponse>?
)

data class TelegramUpdateResultResponse(
    @SerializedName("update_id")
    val updateId: Long?,
    @SerializedName("message")
    val message: TelegramMessageResponse?
)

data class TelegramMessageResponse(
    @SerializedName("message_id")
    val id: Long?,
    @SerializedName("date")
    val date: Long?,
    @SerializedName("text")
    val text: String?
)