package com.watch.movies.data.repository

import com.watch.movies.data.datasources.MoviesDataSources
import com.watch.movies.domain.SuccessLoadingMovies

internal class MoviesRepositoryImpl(
    private val localDS: MoviesDataSources.LocalDS,
    private val remoteDS: MoviesDataSources.RemoteDS
) : MoviesRepository {

    override suspend fun loadPopularMovies() =
        localDS.loadPopularMovies().let { result ->
            if (result is SuccessLoadingMovies) result
            else remoteDS.loadPopularMovies()
        }

}