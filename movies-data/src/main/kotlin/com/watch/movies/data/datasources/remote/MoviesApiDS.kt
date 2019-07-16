package com.watch.movies.data.datasources.remote

import com.watch.movies.data.datasources.MoviesDataSources
import com.watch.movies.data.datasources.toDM
import com.watch.movies.domain.ErrorLoadingMoviesDM
import com.watch.movies.domain.LoadMoviesResultDM
import com.watch.movies.domain.NoMoviesFoundDM
import com.watch.movies.domain.SuccessLoadingMoviesDM

internal class MoviesApiDS(private val service: TmdbApiService) : MoviesDataSources.RemoteDS {

    override suspend fun loadPopularMovies(): LoadMoviesResultDM =
        with(service.loadPopularMovies()) {
            val response = body()
            if (isSuccessful && response != null) {
                if (response.hasMovies()) SuccessLoadingMoviesDM(response.results.toDM())
                else NoMoviesFoundDM
            } else ErrorLoadingMoviesDM
        }
}