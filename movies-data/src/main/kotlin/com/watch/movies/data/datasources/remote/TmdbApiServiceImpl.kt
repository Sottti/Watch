package com.watch.movies.data.datasources.remote

import com.squareup.moshi.Moshi
import com.watch.movies.data.BuildConfig
import com.watch.movies.data.datasources.MovieOverviewAM
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

internal class TmdbApiServiceImpl : TmdbApiService {

    companion object {
        const val baseUrl = "https://api.themoviedb.org/3"
    }

    override suspend fun loadPopularMovies(): Response<Set<MovieOverviewAM>> {
        return service.loadPopularMovies()
    }

    private interface TmdbApiService {
        @GET("/discover/movie")
        suspend fun loadPopularMovies(
            @Query("sort_by") sorting: String = "popularity.desc"
        ): Response<Set<MovieOverviewAM>>
    }

    private val service by lazy {
        retrofit.create(TmdbApiService::class.java)
    }

    private val retrofit: Retrofit by lazy {
        Retrofit
            .Builder()
            .client(okHttp)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(TmdbInterceptor())
            .build()
    }

    private val httpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC
            else HttpLoggingInterceptor.Level.NONE
        }
    }

    private val moshi: Moshi by lazy {
        Moshi
            .Builder()
            .build()
    }

}