package com.sotti.watch.movies.data.repository

import com.sotti.watch.movies.data.datasources.MoviesDataSources
import com.sotti.watch.movies.domain.SuccessLoadingMoviesDM

internal class MoviesRepositoryImpl(
    private val localDS: MoviesDataSources.LocalDS,
    private val remoteDS: MoviesDataSources.RemoteDS
) : MoviesRepository {

    override suspend fun loadPopularMovies() =
        localDS.loadPopularMovies().let { result ->
            if (result is SuccessLoadingMoviesDM) result
            else remoteDS.loadPopularMovies()
        }

}