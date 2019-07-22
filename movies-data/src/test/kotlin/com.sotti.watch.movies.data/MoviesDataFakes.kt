package com.sotti.watch.movies.data

import com.sotti.watch.movies.data.datasources.MovieOverviewAM
import com.sotti.watch.movies.data.datasources.PopularMoviesResponseAM
import com.sotti.watch.movies.data.datasources.toDM
import com.sotti.watch.movies.domain.MovieOverviewDM
import com.sotti.watch.movies.domain.SuccessLoadingMoviesDM
import com.sotti.watch.utils.loadJsonAsText
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

internal object MoviesDataFakes {

    val theMatrixAM = MovieOverviewAM(
        id = 603,
        title = "The Matrix",
        posterPath = "/lh4aGpd3U9rm9B8Oqr6CUgQLtZL.jpg",
        voteAverage = 8.1f,
        overview = "Set in the 22nd century, The Matrix tells the story of a computer hacker who" +
                " joins a group of underground insurgents fighting the vast and powerful computers" +
                " who now rule the earth."
    )

    val theMatrixDM = MovieOverviewDM(
        id = 603,
        title = "The Matrix",
        posterPath = "/lh4aGpd3U9rm9B8Oqr6CUgQLtZL.jpg",
        voteAverage = 8.1f,
        overview = "Set in the 22nd century, The Matrix tells the story of a computer hacker who" +
                " joins a group of underground insurgents fighting the vast and powerful computers" +
                " who now rule the earth."
    )

    val fightClubAM =
        MovieOverviewAM(
            id = 550,
            title = "Fight Club",
            posterPath = "/mMZRKb3NVo5ZeSPEIaNW9buLWQ0.jpg",
            voteAverage = 8.4f,
            overview = "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male" +
                    " aggression into a shocking new form of therapy. Their concept catches on, with" +
                    " underground \\\"fight clubs\\\" forming in every town, until an eccentric gets in" +
                    " the way and ignites an out-of-control spiral toward oblivion."
        )

    val fightClubDM =
        MovieOverviewDM(
            id = 550,
            title = "Fight Club",
            posterPath = "/mMZRKb3NVo5ZeSPEIaNW9buLWQ0.jpg",
            voteAverage = 8.4f,
            overview = "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male" +
                    " aggression into a shocking new form of therapy. Their concept catches on, with" +
                    " underground \\\"fight clubs\\\" forming in every town, until an eccentric gets in" +
                    " the way and ignites an out-of-control spiral toward oblivion."
        )

    val popularMovies = PopularMoviesResponseAM(
        page = 1,
        totalResults = 2,
        totalPages = 3,
        results = setOf(theMatrixAM)
    )

    private val popularMovies_emptySet = PopularMoviesResponseAM(
        page = 1,
        totalResults = 1,
        totalPages = 1,
        results = emptySet()
    )

    val servicePopularMoviesResponse_withMovies = Response.success(popularMovies)
    val popularMoviesResponse_notFound = Response.success(popularMovies_emptySet)
    val popularMoviesResponse_null = Response.success<PopularMoviesResponseAM>(null)
    val popularMoviesResponse_error = Response.error<PopularMoviesResponseAM>(404, "".toResponseBody())

    val apiDSResult = SuccessLoadingMoviesDM(popularMovies.results.toDM())

    val actualPopularMoviesJsonResponse = javaClass.loadJsonAsText("/popularMoviesResponse.json")

}
