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
    val id: Int
)
