package com.sotti.watch.explore.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sotti.watch.movies.data.repository.MoviesRepository
import com.sotti.watch.utils.SingleLiveAction
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class ExploreViewModelImpl(
    private val moviesRepository: MoviesRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ExploreViewModel() {

    private val _viewEvents = SingleLiveAction<ExploreViewEventUIM>()
    override val viewEvent: LiveData<ExploreViewEventUIM> = _viewEvents

    private val _viewState = MutableLiveData<ExploreViewStateUIM>()
    override val viewState: LiveData<ExploreViewStateUIM>
        get() {
            loadPopularMovies()
            return _viewState
        }

    private fun loadPopularMovies() {
        _viewState.value = LoadingUIM
        viewModelScope.launch {
            _viewState.value = withContext(dispatcher) {
                return@withContext moviesRepository.loadPopularMovies().toUiModel()
            }
        }
    }

    override fun onMovieShowDetails(movieId: Int) {
        _viewEvents.value = ShowMovieDetails(movieId)
    }

    override fun onRetry() {
        loadPopularMovies()
    }
}