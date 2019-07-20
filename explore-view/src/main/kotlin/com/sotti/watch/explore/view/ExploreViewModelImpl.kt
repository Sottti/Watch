package com.sotti.watch.explore.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sotti.watch.movies.data.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class ExploreViewModelImpl(private val moviesRepository: MoviesRepository) :
    ExploreViewModel() {

    override val _viewState = MutableLiveData<ExploreViewStateUIM>()
    override val viewState: LiveData<ExploreViewStateUIM>
        get() {
            loadPopularMovies()
            return _viewState
        }

    private fun loadPopularMovies() {
        _viewState.value = LoadingUIM
        viewModelScope.launch {
            _viewState.value = withContext(Dispatchers.IO) {
                return@withContext moviesRepository.loadPopularMovies().toUiModel()
            }
        }
    }

    override fun onRetry() {
        loadPopularMovies()
    }
}