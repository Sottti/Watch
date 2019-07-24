package com.sotti.explore.view

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.sotti.watch.android.tests.common.asLandscape
import com.sotti.watch.android.tests.common.asPortrait
import com.sotti.watch.explore.view.ExploreFragment
import com.sotti.watch.explore.view.ExploreFragment.Companion.ARG_AVOID_INJECTIONS
import com.sotti.watch.explore.view.ExploreViewModel
import com.sotti.watch.explore.view.MovieOverviewUIM
import com.sotti.watch.explore.view.R
import com.sotti.watch.explore.view.list.ExploreMovieVH
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

@LargeTest
@RunWith(AndroidJUnit4::class)
internal class ExploreViewTests {

    private val viewModelFake = FakeExploreViewModel()
    private lateinit var scenario: FragmentScenario<ExploreFragment>

    private val progressBar = onView(ViewMatchers.withId(R.id.progress_bar))

    private val content = onView(ViewMatchers.withId(R.id.content))
    private val listItemPoster = onView(ViewMatchers.withId(R.id.poster))
    private val listItemTitle = onView(ViewMatchers.withId(R.id.title))
    private val listItemVoteAverageIcon = onView(ViewMatchers.withId(R.id.vote_average_icon))
    private val listItemVoteAverage = onView(ViewMatchers.withId(R.id.vote_average))
    private val listItemOverview = onView(ViewMatchers.withId(R.id.overview))

    private val emptyView = onView(ViewMatchers.withId(R.id.empty_view))
    private val emptyViewImage = onView(ViewMatchers.withId(R.id.empty_view_image))
    private val emptyViewTitle = onView(ViewMatchers.withId(R.id.empty_view_title))
    private val emptyViewSubtitle = onView(ViewMatchers.withId(R.id.empty_view_subtitle))

    private val errorView = onView(ViewMatchers.withId(R.id.error_view))
    private val errorViewImage = onView(ViewMatchers.withId(R.id.error_view_image))
    private val errorViewSubtitle = onView(ViewMatchers.withId(R.id.error_view_title))
    private val errorViewTitle = onView(ViewMatchers.withId(R.id.error_view_subtitle))
    private val errorViewRetryButton = onView(ViewMatchers.withId(R.id.error_view_retry))

    @Before
    fun setUp() {
        startKoin {

        }

        loadKoinModules(module {
            viewModel {
                @Suppress("USELESS_CAST")
                viewModelFake as ExploreViewModel
            }
        })
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun showsProgressBar_portrait() {
        viewModelFake.setMode(FakeExploreViewModel.Mode.LOADING)
        launchFragment().asPortrait()
        checkJustProgressBarIsVisible()
    }

    @Test
    fun showsProgressBar_landscape() {
        viewModelFake.setMode(FakeExploreViewModel.Mode.LOADING)
        launchFragment().asLandscape()
        checkJustProgressBarIsVisible()
    }

    private fun checkJustProgressBarIsVisible() {
        progressBar.check(matches(isCompletelyDisplayed()))
        content.check(matches(not(isCompletelyDisplayed())))
        errorView.check(matches(not(isCompletelyDisplayed())))
        emptyView.check(matches(not(isCompletelyDisplayed())))
    }

    @Test
    fun showsErrorView_portrait() {
        viewModelFake.setMode(FakeExploreViewModel.Mode.ERROR)
        launchFragment().asPortrait()
        checkJustErrorViewIsVisible()
        checkErrorViewElementsAreVisible()
    }

    @Test
    fun showsErrorView_landscape() {
        viewModelFake.setMode(FakeExploreViewModel.Mode.ERROR)
        launchFragment().asLandscape()
        checkJustErrorViewIsVisible()
        checkErrorViewElementsAreVisible()
    }

    private fun checkJustErrorViewIsVisible() {
        errorView.check(matches(isCompletelyDisplayed()))
        content.check(matches(not(isCompletelyDisplayed())))
        emptyView.check(matches(not(isCompletelyDisplayed())))
        progressBar.check(matches(not(isCompletelyDisplayed())))
    }

    private fun checkErrorViewElementsAreVisible() {
        errorView.check(matches(isCompletelyDisplayed()))
        errorViewImage.check(matches(isCompletelyDisplayed()))
        errorViewTitle.check(matches(isCompletelyDisplayed()))
        errorViewSubtitle.check(matches(isCompletelyDisplayed()))
        errorViewRetryButton.check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun showsEmptyView_portrait() {
        viewModelFake.setMode(FakeExploreViewModel.Mode.EMPTY)
        launchFragment().asPortrait()
        checkJustEmptyViewIsVisible()
        checkEmptyViewElementsAreVisible()
    }

    @Test
    fun showsEmptyView_landscape() {
        viewModelFake.setMode(FakeExploreViewModel.Mode.EMPTY)
        launchFragment().asLandscape()
        checkJustEmptyViewIsVisible()
        checkEmptyViewElementsAreVisible()
    }

    private fun checkJustEmptyViewIsVisible() {
        emptyView.check(matches(isCompletelyDisplayed()))
        content.check(matches(not(isCompletelyDisplayed())))
        errorView.check(matches(not(isCompletelyDisplayed())))
        progressBar.check(matches(not(isCompletelyDisplayed())))
    }

    private fun checkEmptyViewElementsAreVisible() {
        emptyView.check(matches(isCompletelyDisplayed()))
        emptyViewImage.check(matches(isCompletelyDisplayed()))
        emptyViewTitle.check(matches(isCompletelyDisplayed()))
        emptyViewSubtitle.check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun listItemContentIsVisible_portrait() {
        viewModelFake.setMode(FakeExploreViewModel.Mode.CONTENT)
        viewModelFake.setContent(singleMovieMatrix)
        launchFragment().asPortrait()
        checkJustContentIsVisible()
        checkListItemContent(singleMovieMatrix[0])
    }

    @Test
    fun listItemContentIsVisible_landscape() {
        viewModelFake.setMode(FakeExploreViewModel.Mode.CONTENT)
        viewModelFake.setContent(singleMovieMatrix)
        launchFragment().asLandscape()
        checkJustContentIsVisible()
        checkListItemContent(singleMovieMatrix[0])
    }

    private fun checkJustContentIsVisible() {
        content.check(matches(isCompletelyDisplayed()))
        emptyView.check(matches(not(isCompletelyDisplayed())))
        errorView.check(matches(not(isCompletelyDisplayed())))
        progressBar.check(matches(not(isCompletelyDisplayed())))
    }

    private fun checkListItemContent(movie: MovieOverviewUIM) {
        content.perform(RecyclerViewActions.scrollToPosition<ExploreMovieVH>(0))
        listItemPoster.check(matches(isCompletelyDisplayed()))
        listItemVoteAverageIcon.check(matches(isCompletelyDisplayed()))
        listItemTitle.check(matches(allOf(withText(movie.title), isCompletelyDisplayed())))
        listItemOverview.check(matches(allOf(withText(movie.overview), isCompletelyDisplayed())))
        listItemVoteAverage.check(
            matches(
                allOf(
                    withText(movie.voteAverage.toString()),
                    isCompletelyDisplayed()
                )
            )
        )
    }

    @Test
    fun contentCanScroll_portrait() {
        viewModelFake.setMode(FakeExploreViewModel.Mode.CONTENT)
        viewModelFake.setContent(twentyMovies)
        launchFragment().asPortrait()
        checkCanScroll()
    }

    @Test
    fun contentCanScroll_landscape() {
        viewModelFake.setMode(FakeExploreViewModel.Mode.CONTENT)
        viewModelFake.setContent(twentyMovies)
        launchFragment().asPortrait()
        checkCanScroll()
    }

    private fun checkCanScroll() {
        content.perform(RecyclerViewActions.scrollToPosition<ExploreMovieVH>(0))
        onView(withText(twentyMovies[0].title)).check(matches(isCompletelyDisplayed()))
        content.perform(RecyclerViewActions.scrollToPosition<ExploreMovieVH>(twentyMovies.size - 1))
        onView(withText(twentyMovies[twentyMovies.size - 1].title)).check(
            matches(
                isCompletelyDisplayed()
            )
        )
    }

    @Test
    fun checkRetryWorks_portrait() {
        viewModelFake.setMode(FakeExploreViewModel.Mode.RETRY)
        viewModelFake.setContent(twentyMovies)
        launchFragment().asPortrait()
        checkRetryWorks()
    }

    @Test
    fun checkRetryWorks_landscape() {
        viewModelFake.setMode(FakeExploreViewModel.Mode.RETRY)
        viewModelFake.setContent(twentyMovies)
        launchFragment().asLandscape()
        checkRetryWorks()
    }

    private fun checkRetryWorks() {
        checkJustErrorViewIsVisible()
        errorViewRetryButton.perform(click())
        checkJustContentIsVisible()
    }

    private fun launchFragment(): FragmentScenario<ExploreFragment> {
        scenario =
            launchFragmentInContainer(
                fragmentArgs = Bundle().apply { putBoolean(ARG_AVOID_INJECTIONS, true) },
                themeResId = R.style.WatchAppTheme
            )
        return scenario
    }
}