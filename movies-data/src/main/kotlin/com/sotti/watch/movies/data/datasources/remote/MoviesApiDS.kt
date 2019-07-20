package com.sotti.watch.movies.data.datasources.remote

import com.sotti.watch.movies.data.datasources.MoviesDataSources
import com.sotti.watch.movies.data.datasources.toDM
import com.sotti.watch.movies.domain.ErrorLoadingMoviesDM
import com.sotti.watch.movies.domain.LoadMoviesResultDM
import com.sotti.watch.movies.domain.NoMoviesFoundDM
import com.sotti.watch.movies.domain.SuccessLoadingMoviesDM

internal class MoviesApiDS(private val service: TmdbApiService) : MoviesDataSources.RemoteDS {

    override suspend fun loadPopularMovies(): LoadMoviesResultDM =
        with(service.loadPopularMovies()) {
            val response = body()
            if (isSuccessful && response != null) {
                if (response.hasMovies()) SuccessLoadingMoviesDM(
                    response.results.toDM()
                )
                else NoMoviesFoundDM
            } else ErrorLoadingMoviesDM
        }
}