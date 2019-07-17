package com.sotti.watch.explore.view

import android.view.View

internal sealed class ExploreViewStateUIM {
    abstract val isProgressBarVisible: Boolean
    abstract val isErrorMessageVisible: Boolean
    abstract val isMoviesCounterVisible: Boolean
    abstract val isEmptyContentMessageVisible: Boolean
    abstract val movies: List<MovieOverviewUIM>
}

internal object LoadingUIM : ExploreViewStateUIM() {
    override val isProgressBarVisible = true
    override val isErrorMessageVisible = false
    override val isMoviesCounterVisible = false
    override val isEmptyContentMessageVisible = false
    override val movies = emptyList<MovieOverviewUIM>()
}

internal object ErrorLoadingUIM : ExploreViewStateUIM() {
    override val isProgressBarVisible = false
    override val isErrorMessageVisible = true
    override val isMoviesCounterVisible = false
    override val isEmptyContentMessageVisible = false
    override val movies = emptyList<MovieOverviewUIM>()
}

internal object NoMoviesFoundUIM : ExploreViewStateUIM() {
    override val isProgressBarVisible = false
    override val isErrorMessageVisible = false
    override val isMoviesCounterVisible = false
    override val isEmptyContentMessageVisible = true
    override val movies = emptyList<MovieOverviewUIM>()
}

internal data class SuccessLoadingUIM(
    override val movies: List<MovieOverviewUIM>
) : ExploreViewStateUIM() {
    override val isProgressBarVisible = false
    override val isErrorMessageVisible = false
    override val isMoviesCounterVisible = true
    override val isEmptyContentMessageVisible = false
}

internal class ExploreViewStateUIMDecorator(viewState: ExploreViewStateUIM) {
    val progressBarVisibility = if (viewState.isProgressBarVisible) View.VISIBLE else View.GONE
    val errorMessageVisibility = if (viewState.isErrorMessageVisible) View.VISIBLE else View.GONE
    val emptyContentMessageVisibility = if (viewState.isEmptyContentMessageVisible) View.VISIBLE else View.GONE
}

internal sealed class ExploreListItemUIM

internal data class MovieOverviewUIM(
    val id: Int,
    val title : String,
    val posterPath: String,
    val voteAverage : Float
) : ExploreListItemUIM() {
    val voteAverageString = voteAverage.toString()
}