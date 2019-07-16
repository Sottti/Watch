package com.watch.movies.domain

sealed class LoadMoviesResultDM
data class SuccessLoadingMoviesDM(val movies: Set<MovieOverviewDM>) : LoadMoviesResultDM()
object ErrorLoadingMoviesDM : LoadMoviesResultDM()
object NoMoviesFoundDM : LoadMoviesResultDM()

data class MovieOverviewDM(val id: Int)