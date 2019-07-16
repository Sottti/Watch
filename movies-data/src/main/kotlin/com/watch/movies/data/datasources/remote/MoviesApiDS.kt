package com.watch.movies.data.datasources.remote

import com.watch.movies.data.datasources.MoviesDataSources
import com.watch.movies.domain.NoMoviesFound

internal class MoviesApiDS(private val service: TmdbApiService) : MoviesDataSources.RemoteDS {
    override suspend fun loadPopularMovies() = NoMoviesFound
}