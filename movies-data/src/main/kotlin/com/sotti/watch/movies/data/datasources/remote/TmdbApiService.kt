package com.sotti.watch.movies.data.datasources.remote

import com.sotti.watch.movies.data.datasources.PopularMoviesResponseAM
import retrofit2.Response

internal interface TmdbApiService {
    suspend fun loadPopularMovies(): Response<PopularMoviesResponseAM>
}