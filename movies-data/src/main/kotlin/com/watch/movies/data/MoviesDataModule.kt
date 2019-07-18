package com.watch.movies.data

import com.sotti.watch.network.common.networkCommonModule
import com.watch.movies.data.datasources.MoviesDataSources
import com.watch.movies.data.datasources.local.MoviesRoomDS
import com.watch.movies.data.datasources.remote.MoviesApiDS
import com.watch.movies.data.datasources.remote.TmdbApiService
import com.watch.movies.data.datasources.remote.TmdbApiServiceImpl
import com.watch.movies.data.repository.MoviesRepository
import com.watch.movies.data.repository.MoviesRepositoryImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectMoviesRepositoryModules() = loadMoviesRepositoryModules

private val loadMoviesRepositoryModules by lazy {
    loadKoinModules(listOf(moviesRepositoryModule, networkCommonModule))
}

private val moviesRepositoryModule = module {
    single<MoviesRepository> { MoviesRepositoryImpl(localDS = get(), remoteDS = get()) }
    single<MoviesDataSources.LocalDS> { MoviesRoomDS() }
    single<MoviesDataSources.RemoteDS> { MoviesApiDS(service = get()) }
    single<TmdbApiService> { TmdbApiServiceImpl(retrofit = get()) }
}