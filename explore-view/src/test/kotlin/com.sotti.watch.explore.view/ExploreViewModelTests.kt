package com.sotti.watch.explore.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sotti.watch.movies.data.repository.MoviesRepository
import com.sotti.watch.movies.domain.ErrorLoadingMoviesDM
import com.sotti.watch.movies.domain.NoMoviesFoundDM
import com.sotti.watch.movies.domain.SuccessLoadingMoviesDM
import com.sotti.watch.tests.common.CoroutinesTestRule
import com.sotti.watch.tests.common.captureValues
import com.sotti.watch.tests.common.getValueForTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
internal class ExploreViewModelTests {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var moviesRepository: MoviesRepository

    private lateinit var viewModel: ExploreViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExploreViewModelImpl(moviesRepository, Dispatchers.Main)
    }

    @Test
    fun whenNoMoviesFound_returnsNoMoviesFound() {
        with(coroutinesTestRule.testDispatcher) {
            runBlockingTest {
                `when`(moviesRepository.loadPopularMovies()).thenReturn(NoMoviesFoundDM)
                pauseDispatcher()
                viewModel.viewState.captureValues {
                    assertSendsValues(LoadingUIM)
                    resumeDispatcher()
                    assertSendsValues(LoadingUIM, NoMoviesFoundUIM)
                    verify(moviesRepository, times(1)).loadPopularMovies()
                }
            }
        }
    }

    @Test
    fun whenErrorLoadingMovies_returnsErrorLoadingMovies() {
        with(coroutinesTestRule.testDispatcher) {
            runBlockingTest {
                `when`(moviesRepository.loadPopularMovies()).thenReturn(ErrorLoadingMoviesDM)
                pauseDispatcher()
                viewModel.viewState.captureValues {
                    assertSendsValues(LoadingUIM)
                    resumeDispatcher()
                    assertSendsValues(LoadingUIM, ErrorLoadingUIM)
                    verify(moviesRepository, times(1)).loadPopularMovies()
                }
            }
        }
    }

    @Test
    fun whenSuccessLoadingMovie_returnsSuccessLoadingMovies() {
        with(coroutinesTestRule.testDispatcher) {
            runBlockingTest {
                `when`(moviesRepository.loadPopularMovies()).thenReturn(
                    SuccessLoadingMoviesDM(
                        emptySet()
                    )
                )
                pauseDispatcher()
                viewModel.viewState.captureValues {
                    assertSendsValues(LoadingUIM)
                    resumeDispatcher()
                    assertSendsValues(LoadingUIM, SuccessLoadingUIM(emptyList()))
                    verify(moviesRepository, times(1)).loadPopularMovies()
                }
            }
        }
    }

    @Test
    fun whenRetry_thenRepositoryIsQueried() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            `when`(moviesRepository.loadPopularMovies()).thenReturn(NoMoviesFoundDM)
            viewModel.onRetry()
            verify(moviesRepository, times(1)).loadPopularMovies()
        }
    }
}