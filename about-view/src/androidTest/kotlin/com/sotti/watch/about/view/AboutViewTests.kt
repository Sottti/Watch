package com.sotti.watch.about.view


import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.sotti.watch.about.view.AboutFragment.Companion.ARG_AVOID_INJECTIONS
import com.sotti.watch.android.tests.common.asLandscape
import com.sotti.watch.android.tests.common.asPortrait
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

@LargeTest
@RunWith(AndroidJUnit4::class)
internal class AboutViewTests {

    private lateinit var scenario: FragmentScenario<AboutFragment>

    private val coverImage = onView(withId(R.id.cover_image))
    private val profileImage = onView(withId(R.id.profile_image))

    private val card = onView(withId(R.id.card))
    private val name = onView(withId(R.id.name))
    private val location = onView(withId(R.id.location))
    private val occupation = onView(withId(R.id.occupation))

    private val githubRow = onView(withId(R.id.github))
    private val stackOverflowRow = onView(withId(R.id.stackoverflow))
    private val mediumRow = onView(withId(R.id.medium))
    private val twitterRow = onView(withId(R.id.twitter))
    private val linkedInRow = onView(withId(R.id.linkedin))

    @Before
    fun setUp() {
        startKoin {

        }

        loadKoinModules(aboutViewModule)

        scenario =
            launchFragmentInContainer(
                fragmentArgs = Bundle().apply { putBoolean(ARG_AVOID_INJECTIONS, true) },
                themeResId = R.style.WatchAppTheme
            )
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun visibility_portrait() {
        scenario.asPortrait()
        checkAllVisibilities()
    }

    @Test
    fun visibility_landscape() {
        scenario.asLandscape()
        checkAllVisibilities()
    }

    private fun checkAllVisibilities() {
        coverImage.check(matches(isCompletelyDisplayed()))
        profileImage.check(matches(isCompletelyDisplayed()))
        card.check(matches(isCompletelyDisplayed()))
        name.check(matches(isCompletelyDisplayed()))
        location.check(matches(isCompletelyDisplayed()))
        occupation.check(matches(isCompletelyDisplayed()))


        githubRow.perform(ViewActions.scrollTo()).check(matches(isCompletelyDisplayed()))
        stackOverflowRow.perform(ViewActions.scrollTo()).check(matches(isCompletelyDisplayed()))
        mediumRow.perform(ViewActions.scrollTo()).check(matches(isCompletelyDisplayed()))
        linkedInRow.perform(ViewActions.scrollTo()).check(matches(isCompletelyDisplayed()))
        twitterRow.perform(ViewActions.scrollTo()).check(matches(isCompletelyDisplayed()))
    }
}