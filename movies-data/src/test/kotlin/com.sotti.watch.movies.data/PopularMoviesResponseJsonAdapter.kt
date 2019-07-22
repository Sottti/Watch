package com.sotti.watch.movies.data

import com.sotti.watch.movies.data.MoviesDataFakes.actualPopularMoviesJsonResponse
import com.sotti.watch.movies.data.MoviesDataFakes.popularMovies
import com.sotti.watch.movies.data.datasources.PopularMoviesResponseAM
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.junit.Test

internal class PopularMoviesResponseJsonAdapter {

    private val adapter: JsonAdapter<PopularMoviesResponseAM> =
        Moshi.Builder().build()
            .adapter(PopularMoviesResponseAM::class.java)

    @Test
    fun popularMoviesResponseAMDeserializer() {
        with(adapter.fromJson(actualPopularMoviesJsonResponse)) {
            org.junit.Assert.assertNotNull(this)
            org.junit.Assert.assertEquals(popularMovies, this)
        }
    }
}