package com.sotti.watch.explore.view

import com.sotti.watch.movies.data.injectMoviesRepositoryModules
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

internal fun injectExploreModules() = loadExploreModules

private val loadExploreModules by lazy {
    loadKoinModules(exploreViewModule)
    injectMoviesRepositoryModules()
}

private val exploreViewModule = module {
    viewModel<ExploreViewModel> { ExploreViewModelImpl(moviesRepository = get()) }
}