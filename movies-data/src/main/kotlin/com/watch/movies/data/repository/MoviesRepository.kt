package com.watch.movies.data.repository

import com.watch.movies.domain.LoadMoviesResultDM

interface MoviesRepository {
    suspend fun loadPopularMovies(): LoadMoviesResultDM
}