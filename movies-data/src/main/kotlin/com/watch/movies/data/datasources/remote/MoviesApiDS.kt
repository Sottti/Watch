package com.watch.movies.data.datasources.remote

import com.watch.movies.data.datasources.MoviesDataSources
import com.watch.movies.domain.LoadMoviesResultDM
import com.watch.movies.domain.NoMoviesFound

internal class MoviesApiDS : MoviesDataSources.RemoteDS {
    override suspend fun loadPopularMovies() = NoMoviesFound
}