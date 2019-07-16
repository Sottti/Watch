package com.sotti.watch.network.common

import android.content.Context
import com.squareup.moshi.Moshi
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val baseUrl = "https://api.themoviedb.org/3"
private const val cacheSize: Long = 25 * 1024 * 1024

fun getCommonRetrofit(context: Context): Retrofit = initRetrofit(context)

private fun initRetrofit(context: Context) =
    Retrofit
        .Builder()
        .client(initOkHttp(context))
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()


private fun initOkHttp(context: Context) =
    OkHttpClient
        .Builder()
        .cache(Cache(context.cacheDir, cacheSize))
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(TmdbInterceptor())
        .build()

private val moshi: Moshi by lazy {
    Moshi
        .Builder()
        .build()
}

private val httpLoggingInterceptor by lazy {
    HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC else HttpLoggingInterceptor.Level.NONE
    }
}