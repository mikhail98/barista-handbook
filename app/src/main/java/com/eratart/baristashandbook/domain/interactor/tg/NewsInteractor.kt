package com.eratart.baristashandbook.domain.interactor.tg

import com.eratart.baristashandbook.domain.model.NewsBot
import com.eratart.baristashandbook.domain.repository.INewsRepo

class NewsInteractor(private val tgRepo: INewsRepo) : INewsInteractor {

    override suspend fun getNews(): List<NewsBot> {
        return tgRepo.getNews()
    }
}