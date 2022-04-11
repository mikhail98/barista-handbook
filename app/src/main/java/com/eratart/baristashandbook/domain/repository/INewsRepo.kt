package com.eratart.baristashandbook.domain.repository

import com.eratart.baristashandbook.domain.model.NewsBot

interface INewsRepo {

    suspend fun getNews(): List<NewsBot>
}