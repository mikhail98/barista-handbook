package com.eratart.baristashandbook.domain.repository

import com.eratart.baristashandbook.domain.model.NewsBot
import kotlinx.coroutines.flow.Flow

interface INewsRepo {

    suspend fun getNews(): Flow<List<NewsBot>>
}