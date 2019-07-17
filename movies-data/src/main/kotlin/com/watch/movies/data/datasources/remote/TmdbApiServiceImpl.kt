package com.watch.movies.data.datasources.remote

import com.watch.movies.data.datasources.PopularMoviesResponseAM
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

internal class TmdbApiServiceImpl(retrofit: Retrofit) : TmdbApiService {

    override suspend fun loadPopularMovies() = service.loadPopularMovies()

    private interface TmdbApiService {
        @GET("discover/movie")
        suspend fun loadPopularMovies(
            @Query("sort_by") sorting: String = "popularity.desc"
        ): Response<PopularMoviesResponseAM>
    }

    private val service by lazy {
        retrofit.create(TmdbApiService::class.java)
    }
}