package com.sotti.explore.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sotti.watch.explore.view.*

internal class FakeExploreViewModel : ExploreViewModel() {

    enum class Mode {
        LOADING,
        ERROR,
        EMPTY,
        SUCCESS
    }

    fun setMode(mode : Mode) {
        this.mode = mode
    }

    private var mode: Mode = Mode.LOADING

    override val _viewState = MutableLiveData<ExploreViewStateUIM>()
    override val viewState: LiveData<ExploreViewStateUIM>
        get() {
            loadPopularMovies()
            return _viewState
        }

    private fun loadPopularMovies() {
        when (mode) {
            Mode.LOADING -> _viewState.value = LoadingUIM
            Mode.ERROR -> _viewState.value = ErrorLoadingUIM
            Mode.EMPTY -> _viewState.value = NoMoviesFoundUIM
            Mode.SUCCESS -> _viewState.value =
                SuccessLoadingUIM(
                    listOf(
                        MovieOverviewUIM(
                            id = 1,
                            title = "Title",
                            posterPath = "",
                            voteAverage = 8.0f,
                            overview = ""
                        )
                    )
                )
        }
    }

    override fun onRetry() {

    }
}