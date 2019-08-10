package com.sotti.watch.movies.data

import com.sotti.watch.movies.data.MoviesDataFakes.apiDSResult
import com.sotti.watch.movies.data.MoviesDataFakes.popularMoviesResponse_error
import com.sotti.watch.movies.data.MoviesDataFakes.popularMoviesResponse_notFound
import com.sotti.watch.movies.data.MoviesDataFakes.popularMoviesResponse_null
import com.sotti.watch.movies.data.MoviesDataFakes.servicePopularMoviesResponse_withMovies
import com.sotti.watch.movies.data.datasources.MoviesDataSources
import com.sotti.watch.movies.data.datasources.remote.MoviesApiDS
import com.sotti.watch.movies.data.datasources.remote.TmdbApiService
import com.sotti.watch.movies.domain.ErrorLoadingMoviesDM
import com.sotti.watch.movies.domain.NoMoviesFoundDM
import com.sotti.watch.tests.common.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.internal.verification.VerificationModeFactory

@ExperimentalCoroutinesApi
internal class MoviesApiDSTests {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var service: TmdbApiService

    private lateinit var apiDS: MoviesDataSources.RemoteDS

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        apiDS = MoviesApiDS(service)
    }


    @Test
    fun whenResultIsError_returnError() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            Mockito.`when`(service.loadPopularMovies()).thenReturn(popularMoviesResponse_error)
            Assert.assertEquals(ErrorLoadingMoviesDM, apiDS.loadPopularMovies())
            Mockito.verify(service, VerificationModeFactory.times(1)).loadPopularMovies()
        }
    }

    @Test
    fun whenResultBodyIsNull_returnError() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            Mockito.`when`(service.loadPopularMovies()).thenReturn(popularMoviesResponse_null)
            Assert.assertEquals(ErrorLoadingMoviesDM, apiDS.loadPopularMovies())
            Mockito.verify(service, VerificationModeFactory.times(1)).loadPopularMovies()
        }
    }

    @Test
    fun whenThrowsException_returnError() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            Mockito.`when`(service.loadPopularMovies()).then { throw Exception() }
            Assert.assertEquals(ErrorLoadingMoviesDM, apiDS.loadPopularMovies())
            Mockito.verify(service, VerificationModeFactory.times(1)).loadPopularMovies()
        }
    }

    @Test
    fun whenResultIsEmpty_returnNotFound() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            Mockito.`when`(service.loadPopularMovies()).thenReturn(popularMoviesResponse_notFound)
            Assert.assertEquals(NoMoviesFoundDM, apiDS.loadPopularMovies())
            Mockito.verify(service, VerificationModeFactory.times(1)).loadPopularMovies()
        }
    }

    @Test
    fun whenResultContainsMovies_returnSuccess() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            Mockito.`when`(service.loadPopularMovies())
                .thenReturn(servicePopularMoviesResponse_withMovies)
            Assert.assertEquals(apiDSResult, apiDS.loadPopularMovies())
            Mockito.verify(service, VerificationModeFactory.times(1)).loadPopularMovies()
        }
    }
}