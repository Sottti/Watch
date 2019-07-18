package com.sotti.watch.explore.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat

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
    val emptyContentMessageVisibility =
        if (viewState.isEmptyContentMessageVisible) View.VISIBLE else View.GONE
}

internal sealed class ExploreListItemUIM

internal data class MovieOverviewUIM(
    val id: Int,
    val title: String,
    val posterPath: String,
    val voteAverage: Float,
    val overview: String
) : ExploreListItemUIM() {
    val voteAverageColorResId = when {
        voteAverage < 3 -> R.color.rating_bad
        voteAverage < 5 -> R.color.rating_below_average
        voteAverage < 7 -> R.color.rating_average
        voteAverage < 9 -> R.color.rating_good
        else -> R.color.rating_very_good
    }

    val voteAverageIconResId = when {
        voteAverage < 3 -> R.drawable.ic_round_star_border_18dp
        voteAverage < 7 -> R.drawable.ic_round_star_half_18dp
        else -> R.drawable.ic_round_star_18dp
    }
}

internal class MovieOverViewUIMDecorator(
    private var movieOverview: MovieOverviewUIM,
    private val context: Context
) {
    fun bind(newMovieOverview: MovieOverviewUIM) = apply { movieOverview = newMovieOverview }

    val title: String
        get() = movieOverview.title

    val overview: String
        get() = movieOverview.overview

    val posterPath: String
        get() = movieOverview.posterPath

    val voteAverageString: String
        get() = movieOverview.voteAverage.toString()

    val voteAverageColor: Int
        get() = ContextCompat.getColor(context, movieOverview.voteAverageColorResId)

    val voteAverageIconResId: Drawable?
        get() = ContextCompat.getDrawable(context, movieOverview.voteAverageIconResId)
}