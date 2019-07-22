package com.sotti.watch.movies.data

import com.sotti.watch.movies.data.datasources.MovieOverviewAM
import com.sotti.watch.movies.data.datasources.PopularMoviesResponseAM
import com.sotti.watch.movies.data.datasources.toDM
import com.sotti.watch.movies.domain.SuccessLoadingMoviesDM
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

private val theMatrix = MovieOverviewAM(
    id = 603,
    title = "The Matrix",
    posterPath = "/lh4aGpd3U9rm9B8Oqr6CUgQLtZL.jpg",
    voteAverage = 8.1f,
    overview = "Set in the 22nd century, The Matrix tells the story of a computer hacker who" +
            " joins a group of underground insurgents fighting the vast and powerful computers" +
            " who now rule the earth."
)

internal val popularMovies = PopularMoviesResponseAM(
    page = 1,
    totalResults = 1,
    totalPages = 1,
    results = setOf(theMatrix)
)

internal val popularMovies_emptySet = PopularMoviesResponseAM(
    page = 1,
    totalResults = 1,
    totalPages = 1,
    results = emptySet()
)

internal val servicePopularMoviesResponse_withMovies = Response.success(popularMovies)
internal val popularMoviesResponse_notFound = Response.success(popularMovies_emptySet)
internal val popularMoviesResponse_null = Response.success<PopularMoviesResponseAM>(null)
internal val popularMoviesResponse_error = Response.error<PopularMoviesResponseAM>(404, "".toResponseBody())

internal val apiDSResult = SuccessLoadingMoviesDM(popularMovies.results.toDM())
