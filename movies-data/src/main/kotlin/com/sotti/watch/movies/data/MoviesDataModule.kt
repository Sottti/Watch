package com.sotti.watch.movies.data

import com.sotti.watch.movies.data.datasources.MoviesDataSources
import com.sotti.watch.movies.data.datasources.local.MoviesRoomDS
import com.sotti.watch.movies.data.datasources.remote.MoviesApiDS
import com.sotti.watch.movies.data.datasources.remote.TmdbApiService
import com.sotti.watch.movies.data.datasources.remote.TmdbApiServiceImpl
import com.sotti.watch.movies.data.repository.MoviesRepository
import com.sotti.watch.movies.data.repository.MoviesRepositoryImpl
import com.sotti.watch.network.common.networkCommonModule
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectMoviesRepositoryModules() = loadMoviesRepositoryModules

private val loadMoviesRepositoryModules by lazy {
    loadKoinModules(listOf(moviesRepositoryModule, networkCommonModule))
}

private val moviesRepositoryModule = module {
    single<MoviesRepository> {
        MoviesRepositoryImpl(
            localDS = get(),
            remoteDS = get()
        )
    }
    single<MoviesDataSources.LocalDS> { MoviesRoomDS() }
    single<MoviesDataSources.RemoteDS> {
        MoviesApiDS(
            service = get()
        )
    }
    single<TmdbApiService> {
        TmdbApiServiceImpl(
            retrofit = get()
        )
    }
}