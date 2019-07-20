package com.sotti.watch.movies.data.repository

import com.sotti.watch.movies.domain.LoadMoviesResultDM

interface MoviesRepository {
    suspend fun loadPopularMovies(): LoadMoviesResultDM
}