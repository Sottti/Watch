package com.sotti.watch.movies.data

import com.sotti.watch.movies.data.datasources.MoviesDataSources
import com.sotti.watch.movies.data.repository.MoviesRepository
import com.sotti.watch.movies.data.repository.MoviesRepositoryImpl
import com.sotti.watch.movies.domain.ErrorLoadingMoviesDM
import com.sotti.watch.movies.domain.NoMoviesFoundDM
import com.sotti.watch.movies.domain.SuccessLoadingMoviesDM
import com.sotti.watch.tests.common.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.internal.verification.VerificationModeFactory.times

@ExperimentalCoroutinesApi
internal class MoviesRepositoryTests {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var localDS: MoviesDataSources.LocalDS

    @Mock
    lateinit var remoteDS: MoviesDataSources.RemoteDS

    private lateinit var repository: MoviesRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = MoviesRepositoryImpl(localDS, remoteDS)
    }

    @Test
    fun whenPopularMovies_notFound_returnNotFound() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            Mockito.`when`(localDS.loadPopularMovies()).thenReturn(NoMoviesFoundDM)
            Mockito.`when`(remoteDS.loadPopularMovies()).thenReturn(NoMoviesFoundDM)
            Assert.assertEquals(NoMoviesFoundDM, repository.loadPopularMovies())
            verify(localDS, times(1)).loadPopularMovies()
            verify(remoteDS, times(1)).loadPopularMovies()
        }
    }

    @Test
    fun whenPopularMovies_errorLoading_returnErrorLoading() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            Mockito.`when`(localDS.loadPopularMovies()).thenReturn(ErrorLoadingMoviesDM)
            Mockito.`when`(remoteDS.loadPopularMovies()).thenReturn(ErrorLoadingMoviesDM)
            Assert.assertEquals(ErrorLoadingMoviesDM, repository.loadPopularMovies())
            verify(localDS, times(1)).loadPopularMovies()
            verify(remoteDS, times(1)).loadPopularMovies()
        }
    }

    @Test
    fun whenPopularMovies_foundLocally_returnSuccessWithoutCheckingRemote() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            Mockito.`when`(localDS.loadPopularMovies())
                .thenReturn(SuccessLoadingMoviesDM(emptySet()))
            Assert.assertEquals(SuccessLoadingMoviesDM(emptySet()), repository.loadPopularMovies())
            verify(localDS, times(1)).loadPopularMovies()
            verify(remoteDS, times(0)).loadPopularMovies()
        }
    }

    @Test
    fun whenPopularMovies_foundRemotely_returnSuccessAfterCheckingLocal() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            Mockito.`when`(localDS.loadPopularMovies()).thenReturn(NoMoviesFoundDM)
            Mockito.`when`(remoteDS.loadPopularMovies())
                .thenReturn(SuccessLoadingMoviesDM(emptySet()))
            Assert.assertEquals(SuccessLoadingMoviesDM(emptySet()), repository.loadPopularMovies())
            verify(localDS, times(1)).loadPopularMovies()
            verify(remoteDS, times(1)).loadPopularMovies()
        }
    }
}