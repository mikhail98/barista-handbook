package com.eratart.baristashandbook.data.mapper.repo

import com.eratart.baristashandbook.core.constants.LongConstants
import com.eratart.baristashandbook.data.mapper.IMapper
import com.eratart.baristashandbook.data.model.NewsBotResponse
import com.eratart.baristashandbook.data.model.TelegramMessageResponse
import com.eratart.baristashandbook.domain.model.NewsBot

class NewsFromBotMapper(private val telegramMessageResponse: TelegramMessageResponse) :
    IMapper<NewsBotResponse, NewsBot> {

    override fun mapFrom(item: NewsBotResponse): NewsBot {
        return with(item) {
            val id = telegramMessageResponse.id ?: LongConstants.ZERO
            val mappedDate = if (telegramMessageResponse.date != null) {
                telegramMessageResponse.date * LongConstants.MILLISECONDS_IN_SECOND
            } else {
                System.currentTimeMillis()
            }
            NewsBot(id, mappedDate, title.orEmpty(), text, photoUrl, url)
        }
    }

    override fun mapTo(item: NewsBot): NewsBotResponse {
        return with(item) {
            NewsBotResponse(title, text, photoUrl, url)
        }
    }
}