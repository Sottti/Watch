package com.watch.movies.data.datasources

import com.watch.movies.domain.LoadMoviesResultDM

interface MoviesDataSources {

    interface LocalDS {
        suspend fun loadPopularMovies() : LoadMoviesResultDM
    }

    interface RemoteDS {
        suspend fun loadPopularMovies() : LoadMoviesResultDM
    }

}