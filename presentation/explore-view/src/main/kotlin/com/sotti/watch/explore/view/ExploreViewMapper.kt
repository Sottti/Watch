package com.sotti.watch.explore.view

import com.sotti.watch.movies.domain.*

internal fun LoadMoviesResultDM.toUiModel(): ExploreViewStateUIM =
    when (this) {
        ErrorLoadingMoviesDM -> ErrorLoadingUIM
        NoMoviesFoundDM -> NoMoviesFoundUIM
        is SuccessLoadingMoviesDM -> SuccessLoadingUIM(movies.toUiModel())
    }

internal fun Set<MovieOverviewDM>.toUiModel() = map { it.toUiModel() }

internal fun MovieOverviewDM.toUiModel() =
    MovieOverviewUIM(
        id = id,
        title = title,
        posterPath = posterPath,
        voteAverage = voteAverage,
        overview = overview
    )