package com.watch.movies.data.datasources.remote

import com.watch.movies.data.datasources.MovieOverviewAM
import retrofit2.Response

internal interface TmdbApiService {
    suspend fun loadPopularMovies() : Response<Set<MovieOverviewAM>>
}