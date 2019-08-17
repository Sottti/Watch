package com.sotti.watch.explore.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

internal abstract class ExploreViewModel : ViewModel() {
    @Suppress("PropertyName")
    abstract val viewState: LiveData<ExploreViewStateUIM>

    abstract fun onMovieShowDetails(movieId: Int)
    abstract fun onRetry()
    abstract val viewEvent: LiveData<ExploreViewEventUIM>
}