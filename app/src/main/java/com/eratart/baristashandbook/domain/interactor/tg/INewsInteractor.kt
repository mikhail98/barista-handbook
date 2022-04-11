package com.eratart.baristashandbook.domain.interactor.tg

import com.eratart.baristashandbook.domain.model.NewsBot

interface INewsInteractor {

    suspend fun getNews(): List<NewsBot>
}