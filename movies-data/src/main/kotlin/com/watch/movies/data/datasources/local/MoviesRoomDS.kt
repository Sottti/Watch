package com.watch.movies.data.datasources.local

import com.watch.movies.data.datasources.MoviesDataSources
import com.watch.movies.domain.NoMoviesFound

internal class MoviesRoomDS : MoviesDataSources.LocalDS {
    override suspend fun loadPopularMovies() = NoMoviesFound
}
