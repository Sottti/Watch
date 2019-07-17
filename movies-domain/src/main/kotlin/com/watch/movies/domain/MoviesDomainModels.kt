package com.watch.movies.domain

sealed class LoadMoviesResultDM
object NoMoviesFoundDM : LoadMoviesResultDM()
object ErrorLoadingMoviesDM : LoadMoviesResultDM()
data class SuccessLoadingMoviesDM(val movies: Set<MovieOverviewDM>) : LoadMoviesResultDM()

data class MovieOverviewDM(val id: Int, val title : String, val posterPath : String, val voteAverage : Float)