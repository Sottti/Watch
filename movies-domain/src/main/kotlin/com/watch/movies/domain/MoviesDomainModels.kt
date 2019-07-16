package com.watch.movies.domain

sealed class LoadMoviesResultDM
data class SuccessLoadingMovies(val movies: Set<MovieOverviewDM>) : LoadMoviesResultDM()
object ErrorLoadingMovies : LoadMoviesResultDM()
object NoMoviesFound : LoadMoviesResultDM()

data class MovieOverviewDM(val id: Int)