package com.eratart.baristashandbook.domain.interactor.tg

import com.eratart.baristashandbook.domain.model.NewsBot
import kotlinx.coroutines.flow.Flow

interface INewsInteractor {

    suspend fun getNews(): Flow<List<NewsBot>>
}