package com.sotti.watch.movies.data.datasources.local

import com.sotti.watch.movies.data.datasources.MoviesDataSources
import com.sotti.watch.movies.domain.NoMoviesFoundDM

internal class MoviesRoomDS : MoviesDataSources.LocalDS {
    override suspend fun loadPopularMovies() = NoMoviesFoundDM
}
