package com.sotti.explore.view

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.sotti.watch.explore.view.ExploreFragment
import com.sotti.watch.explore.view.ExploreFragment.Companion.ARG_AVOID_INJECTIONS
import com.sotti.watch.explore.view.ExploreViewModel
import com.sotti.watch.explore.view.R
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

    private val fakeViewModel: ExploreViewModel = FakeExploreViewModel()
    private lateinit var scenario: FragmentScenario<ExploreFragment>

    private val progressBar = Espresso.onView(ViewMatchers.withId(R.id.progress_bar))

    private val errorView = Espresso.onView(ViewMatchers.withId(R.id.error_view))
    private val errorViewImage = Espresso.onView(ViewMatchers.withId(R.id.image))
    private val errorViewSubtitle = Espresso.onView(ViewMatchers.withId(R.id.title))
    private val errorViewTitle = Espresso.onView(ViewMatchers.withId(R.id.subtitle))
    private val errorViewRetryButton = Espresso.onView(ViewMatchers.withId(R.id.retry))

    @Before
    fun setUp() {
        startKoin {

        }

        loadKoinModules(module {
            viewModel { fakeViewModel }
        })
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun showsProgressBar() {
        (fakeViewModel as FakeExploreViewModel).setMode(FakeExploreViewModel.Mode.LOADING)
        launchFragment()
        progressBar.check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
        errorView.check(ViewAssertions.matches(not(ViewMatchers.isCompletelyDisplayed())))
    }

    @Test
    fun showsErrorView() {
        (fakeViewModel as FakeExploreViewModel).setMode(FakeExploreViewModel.Mode.ERROR)
        launchFragment()
        progressBar.check(ViewAssertions.matches(not(ViewMatchers.isCompletelyDisplayed())))
        checkErrorViewElementsAreVisible()
    }

    private fun checkErrorViewElementsAreVisible() {
        errorView.check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
        errorViewImage.check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
        errorViewTitle.check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
        errorViewSubtitle.check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
        errorViewRetryButton.check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
    }

    private fun launchFragment() {
        scenario =
            launchFragmentInContainer(
                fragmentArgs = Bundle().apply { putBoolean(ARG_AVOID_INJECTIONS, true) },
                themeResId = R.style.WatchAppTheme
            )
    }

}