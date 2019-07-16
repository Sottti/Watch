package com.watch.movies.data

import com.watch.movies.data.datasources.MoviesDataSources
import com.watch.movies.data.datasources.local.MoviesRoomDS
import com.watch.movies.data.datasources.remote.MoviesApiDS
import com.watch.movies.data.repository.MoviesRepository
import com.watch.movies.data.repository.MoviesRepositoryImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectMoviesRepositoryModules() = loadMoviesRepositoryModules

private val loadMoviesRepositoryModules by lazy {
    loadKoinModules(moviesRepositoryModule)
}

private val moviesRepositoryModule = module {
    single<MoviesRepository> { MoviesRepositoryImpl(localDS = get(), remoteDS = get()) }
    single<MoviesDataSources.LocalDS> { MoviesRoomDS() }
    single<MoviesDataSources.RemoteDS> { MoviesApiDS() }
}