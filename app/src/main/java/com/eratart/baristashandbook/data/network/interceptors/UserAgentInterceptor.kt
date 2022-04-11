package com.eratart.baristashandbook.data.network.interceptors

import com.eratart.baristashandbook.BuildConfig
import com.eratart.baristashandbook.core.constants.StringConstants
import okhttp3.Interceptor
import okhttp3.Response

class UserAgentInterceptor : Interceptor {

    companion object {
        private const val USER_AGENT_HEADER = "User-Agent"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHeader =
            originalRequest.headers.find { header -> header.first == USER_AGENT_HEADER }?.second
        val userAgent = BuildConfig.APPLICATION_ID
            .plus(StringConstants.SLASH)
            .plus(BuildConfig.VERSION_NAME)
            .plus(StringConstants.SPACE)
            .plus(originalHeader)
        val requestWithUserAgent = originalRequest
            .newBuilder()
            .header(USER_AGENT_HEADER, userAgent)
            .build()
        return chain.proceed(requestWithUserAgent)
    }
}