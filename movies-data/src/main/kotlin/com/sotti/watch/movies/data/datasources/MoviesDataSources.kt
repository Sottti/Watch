package com.sotti.watch.movies.data.datasources

import com.sotti.watch.movies.domain.LoadMoviesResultDM

interface MoviesDataSources {

    interface LocalDS {
        suspend fun loadPopularMovies(): LoadMoviesResultDM
    }

    interface RemoteDS {
        suspend fun loadPopularMovies(): LoadMoviesResultDM
    }

}