package com.watch.movies.data.datasources.remote

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request

internal class TmdbInterceptor : Interceptor {

    private val apiKeyQueryParamName = "api_key"
    private val apiKeyQueryParamValue = "94aa0490aec35aea9986d0c67dbf03b1"

    override fun intercept(chain: Interceptor.Chain) =
        chain.request().let { originalRequest ->
            chain.proceed(
                originalRequest
                    .newBuilder()
                    .url(addApiKeyQuery(originalRequest))
                    .build()
            )
        }

    private fun addApiKeyQuery(request: Request): HttpUrl =
        request
            .url
            .newBuilder()
            .addQueryParameter(apiKeyQueryParamName, apiKeyQueryParamValue)
            .build()
}