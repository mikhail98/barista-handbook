package com.eratart.baristashandbook.data.repository

import com.eratart.baristashandbook.core.ext.printError
import com.eratart.baristashandbook.data.mapper.repo.NewsFromBotMapper
import com.eratart.baristashandbook.data.model.NewsBotResponse
import com.eratart.baristashandbook.data.network.api.TgApi
import com.eratart.baristashandbook.domain.exceptions.ThrowableTransformer
import com.eratart.baristashandbook.domain.model.NewsBot
import com.eratart.baristashandbook.domain.repository.INewsRepo
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class NewsRepo(private val tgApi: TgApi) : INewsRepo {

    companion object {
        private const val TG_BOT_TOKEN = "5227457415:AAFw88n_UDx9G_EaELl-hsqysCTsBZOO5JI"
        private const val CHAT_ID = -715913843
    }

    override suspend fun getNews(): Flow<List<NewsBot>> {
        return flow {
            emit(tgApi.getUpdates(TgApi.getUpdatesUrl(TG_BOT_TOKEN)))
        }
            .map { response ->
                val news = mutableListOf<NewsBot>()
                response.results?.mapNotNull { result -> result.message }
                    ?.filter { message -> message.text != null }
                    ?.forEach { message ->
                        try {
                            val newsResponse =
                                Gson().fromJson(message.text, NewsBotResponse::class.java)
                            news.add(NewsFromBotMapper(message).mapFrom(newsResponse))
                        } catch (e: Exception) {
                            //e.printError()
                        }
                    }
                news.sortedBy { item -> item.date }.asReversed()
            }
            .catch { error ->
                throw ThrowableTransformer.transform(error)
            }
    }
}