package com.eratart.baristashandbook.domain.interactor.tg

import com.eratart.baristashandbook.domain.model.NewsBot
import com.eratart.baristashandbook.domain.repository.INewsRepo
import kotlinx.coroutines.flow.Flow

class NewsInteractor(private val newsRepo: INewsRepo) : INewsInteractor {

    override suspend fun getNews(): Flow<List<NewsBot>> {
        return newsRepo.getNews()
    }
}