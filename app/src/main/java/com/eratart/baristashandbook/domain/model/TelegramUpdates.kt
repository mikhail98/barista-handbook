package com.eratart.baristashandbook.domain.model

data class TelegramUpdates(
    val ok: Boolean,
    val results: List<TelegramUpdateResult>
)

data class TelegramUpdateResult(
    val updateId: Long,
    val message: TelegramMessage?
)

data class TelegramMessage(
    val id: Long,
    val date: Long,
    val text: String?
)