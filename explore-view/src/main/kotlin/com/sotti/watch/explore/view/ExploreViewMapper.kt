package com.sotti.watch.explore.view

import com.watch.movies.domain.ErrorLoadingMoviesDM
import com.watch.movies.domain.LoadMoviesResultDM
import com.watch.movies.domain.NoMoviesFoundDM
import com.watch.movies.domain.SuccessLoadingMoviesDM

internal fun LoadMoviesResultDM.toUiModel(): ExploreViewStateUIM =
    when (this) {
        ErrorLoadingMoviesDM -> ErrorLoadingUIM
        NoMoviesFoundDM -> NoMoviesFoundUIM
        is SuccessLoadingMoviesDM -> SuccessLoadingUIM(movies.size)
    }