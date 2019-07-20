package com.sotti.watch.explore.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

internal abstract class ExploreViewModel : ViewModel() {
    protected abstract val _viewState: MutableLiveData<ExploreViewStateUIM>
    abstract val viewState: LiveData<ExploreViewStateUIM>
    abstract fun onRetry()
}