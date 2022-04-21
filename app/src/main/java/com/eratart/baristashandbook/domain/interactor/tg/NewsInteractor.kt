package com.eratart.baristashandbook.domain.interactor.tg

import com.eratart.baristashandbook.domain.model.NewsBot
import com.eratart.baristashandbook.domain.repository.INewsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class NewsInteractor(private val newsRepo: INewsRepo) : INewsInteractor {

    override suspend fun getNews(): Flow<List<NewsBot>> {
        return newsRepo.getNews().flowOn(Dispatchers.IO)
    }
}