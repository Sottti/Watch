package com.sotti.watch.explore.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sotti.watch.movies.data.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class ExploreViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private val _viewState = MutableLiveData<ExploreViewStateUIM>()
    val viewState: LiveData<ExploreViewStateUIM>
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
}