package com.sotti.watch.explore.view

import android.view.View

internal sealed class ExploreViewStateUIM {
    abstract val isProgressBarVisible: Boolean
    abstract val isErrorMessageVisible: Boolean
    abstract val isMoviesCounterVisible: Boolean
    abstract val isEmptyContentMessageVisible: Boolean
    abstract val moviesCount : Int
}

internal object LoadingUIM : ExploreViewStateUIM() {
    override val moviesCount = 0
    override val isProgressBarVisible = true
    override val isErrorMessageVisible = false
    override val isMoviesCounterVisible = false
    override val isEmptyContentMessageVisible = false
}

internal object ErrorLoadingUIM : ExploreViewStateUIM() {
    override val moviesCount = 0
    override val isProgressBarVisible = false
    override val isErrorMessageVisible = true
    override val isMoviesCounterVisible = false
    override val isEmptyContentMessageVisible = false
}

internal object NoMoviesFoundUIM : ExploreViewStateUIM() {
    override val moviesCount = 0
    override val isProgressBarVisible = false
    override val isErrorMessageVisible = false
    override val isMoviesCounterVisible = false
    override val isEmptyContentMessageVisible = true
}

internal data class SuccessLoadingUIM(val count: Int) : ExploreViewStateUIM() {
    override val moviesCount = count
    override val isProgressBarVisible = false
    override val isErrorMessageVisible = false
    override val isMoviesCounterVisible = true
    override val isEmptyContentMessageVisible = false
}

internal class ExploreViewStateUIMDecorator(viewState: ExploreViewStateUIM) {
    val progressBarVisibility = if (viewState.isProgressBarVisible) View.VISIBLE else View.GONE
    val errorMessageVisibility = if (viewState.isErrorMessageVisible) View.VISIBLE else View.GONE
    val moviesCounterVisibility = if (viewState.isMoviesCounterVisible) View.VISIBLE else View.GONE
    val emptyContentMessageVisibility =
        if (viewState.isEmptyContentMessageVisible) View.VISIBLE else View.GONE
    val moviesCount = viewState.moviesCount.toString()
}