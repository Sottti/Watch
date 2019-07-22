package com.sotti.watch.movies.data

import com.sotti.watch.movies.data.datasources.MoviesDataSources
import com.sotti.watch.movies.data.repository.MoviesRepository
import com.sotti.watch.movies.data.repository.MoviesRepositoryImpl
import com.sotti.watch.tests.common.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

internal class MoviesRepositoryTests {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var localDS : MoviesDataSources.LocalDS

    @Mock
    lateinit var remoteDS : MoviesDataSources.RemoteDS

    private lateinit var repository : MoviesRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = MoviesRepositoryImpl(localDS, remoteDS)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun whenLoadPopularMovies_localAndRemoteAreQueried() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            repository.loadPopularMovies()
            Mockito.verify(localDS).loadPopularMovies()
            Mockito.verify(remoteDS).loadPopularMovies()
        }
    }
}