package com.sotti.explore.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sotti.watch.explore.view.*

internal class FakeExploreViewModel : ExploreViewModel() {

    private var mode: Mode = Mode.LOADING
    private lateinit var movies: List<MovieOverviewUIM>

    override val _viewState = MutableLiveData<ExploreViewStateUIM>()
    override val viewState: LiveData<ExploreViewStateUIM>
        get() {
            loadPopularMovies()
            return _viewState
        }

    enum class Mode {
        LOADING,
        ERROR,
        EMPTY,
        CONTENT
    }

    fun setContent(movies: List<MovieOverviewUIM>) {
        this.movies = movies
    }

    fun setMode(mode: Mode) {
        this.mode = mode
    }

    private fun loadPopularMovies() {
        when (mode) {
            Mode.LOADING -> _viewState.value = LoadingUIM
            Mode.ERROR -> _viewState.value = ErrorLoadingUIM
            Mode.EMPTY -> _viewState.value = NoMoviesFoundUIM
            Mode.CONTENT -> _viewState.value = SuccessLoadingUIM(movies)
        }
    }

    override fun onRetry() {

    }
}