package com.watch.movies.data.repository

import com.watch.movies.data.datasources.MoviesDataSources
import com.watch.movies.domain.LoadMoviesResultDM
import com.watch.movies.domain.SuccessLoadingMovies

internal class MoviesRepositoryImpl(
    private val localDS: MoviesDataSources.LocalDS,
    private val remoteDS: MoviesDataSources.RemoteDS
) : MoviesRepository {
    override suspend fun loadPopularMovies(): LoadMoviesResultDM {
        localDS.loadPopularMovies().let { localMovies ->
            return if (localMovies is SuccessLoadingMovies) localMovies
            else remoteDS.loadPopularMovies()
        }
    }
}