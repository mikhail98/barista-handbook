package com.eratart.baristashandbook.data.network.api


import com.eratart.baristashandbook.data.model.TelegramUpdatesResponse
import retrofit2.http.POST
import retrofit2.http.Url

interface TgApi {

    companion object {
        fun getUpdatesUrl(botToken: String) = "https://api.telegram.org/bot$botToken/getUpdates"
    }

    @POST
    suspend fun getUpdates(@Url url: String): TelegramUpdatesResponse

}