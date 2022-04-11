package com.eratart.baristashandbook.data.network

import com.eratart.baristashandbook.data.network.api.TgApi

interface IRetrofitBuilder {

    fun getTgApi(): TgApi
}