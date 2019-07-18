package com.sotti.watch.app


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
internal class AboutViewTests {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    private val aboutNavItem: ViewInteraction = onView(withId(R.id.nav_destination_about))

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
    fun selectAboutMeTab() {
        aboutNavItem.perform(click())
    }

    @Test
    fun visibility_portrait() {
        setPortraitOrientation(activityTestRule)
        checkAllVisibilities()
    }

    @Test
    fun visibility_landscape() {
        setLandscapeOrientation(activityTestRule)
        checkAllVisibilities()
    }

    private fun checkAllVisibilities() {
        coverImage.check(matches(isCompletelyDisplayed()))
        profileImage.check(matches(isCompletelyDisplayed()))
        card.check(matches(isCompletelyDisplayed()))
        name.check(matches(isCompletelyDisplayed()))
        location.check(matches(isCompletelyDisplayed()))
        occupation.check(matches(isCompletelyDisplayed()))


        githubRow.perform(scrollTo()).check(matches(isCompletelyDisplayed()))
        stackOverflowRow.perform(scrollTo()).check(matches(isCompletelyDisplayed()))
        mediumRow.perform(scrollTo()).check(matches(isCompletelyDisplayed()))
        linkedInRow.perform(scrollTo()).check(matches(isCompletelyDisplayed()))
        twitterRow.perform(scrollTo()).check(matches(isCompletelyDisplayed()))
    }
}
