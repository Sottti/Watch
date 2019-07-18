package com.watch.movies.data.datasources

import com.squareup.moshi.Json

internal data class PopularMoviesResponseAM(

    @field:Json(name = "page")
    val page: Int,

    @field:Json(name = "total_results")
    val totalResults: Int,

    @field:Json(name = "total_pages")
    val totalPages: Int,

    @field:Json(name = "results")
    val results: Set<MovieOverviewAM>
) {
    fun hasMovies() = results.isNotEmpty()
}

internal data class MovieOverviewAM(
    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "title")
    val title : String,

    @field:Json(name = "poster_path")
    val posterPath : String,

    @field:Json(name = "vote_average")
    val voteAverage : Float,

    @field:Json(name = "overview")
    val overview : String
)
