package com.eratart.baristashandbook.data.mapper.repo

import com.eratart.baristashandbook.core.constants.LongConstants
import com.eratart.baristashandbook.data.mapper.IMapper
import com.eratart.baristashandbook.data.model.TelegramMessageResponse
import com.eratart.baristashandbook.data.model.TelegramUpdateResultResponse
import com.eratart.baristashandbook.data.model.TelegramUpdatesResponse
import com.eratart.baristashandbook.domain.model.TelegramMessage
import com.eratart.baristashandbook.domain.model.TelegramUpdateResult
import com.eratart.baristashandbook.domain.model.TelegramUpdates

class TelegramUpdatesMapper : IMapper<TelegramUpdatesResponse, TelegramUpdates> {
    private val mapper = TelegramUpdateResultMapper()
    override fun mapFrom(item: TelegramUpdatesResponse): TelegramUpdates {

        return with(item) {
            val mappedResults = results?.map { result ->
                mapper.mapFrom(result)
            } ?: listOf()
            TelegramUpdates(ok ?: false, mappedResults)
        }
    }

    override fun mapTo(item: TelegramUpdates): TelegramUpdatesResponse {
        return with(item) {
            val mappedResults = results.map { result -> mapper.mapTo(result) }
            TelegramUpdatesResponse(ok, mappedResults)
        }
    }
}

class TelegramUpdateResultMapper : IMapper<TelegramUpdateResultResponse, TelegramUpdateResult> {
    private val mapper = TelegramMessageMapper()
    override fun mapFrom(item: TelegramUpdateResultResponse): TelegramUpdateResult {
        return with(item) {
            TelegramUpdateResult(updateId ?: LongConstants.ZERO, mapper.mapFrom(message))
        }
    }

    override fun mapTo(item: TelegramUpdateResult): TelegramUpdateResultResponse {
        return with(item) {
            TelegramUpdateResultResponse(updateId, mapper.mapTo(message))
        }
    }
}

class TelegramMessageMapper : IMapper<TelegramMessageResponse?, TelegramMessage?> {
    override fun mapFrom(item: TelegramMessageResponse?): TelegramMessage? {
        with(item) {
            val message = this ?: return null
            val mappedDate = if (message.date != null) {
                message.date * LongConstants.MILLISECONDS_IN_SECOND
            } else {
                System.currentTimeMillis()
            }
            return TelegramMessage(message.id ?: LongConstants.ZERO, mappedDate, message.text)
        }
    }

    override fun mapTo(item: TelegramMessage?): TelegramMessageResponse? {
        with(item) {
            val message = this ?: return null
            val mappedDate = message.date / LongConstants.MILLISECONDS_IN_SECOND
            return TelegramMessageResponse(message.id, mappedDate, message.text)
        }
    }
}