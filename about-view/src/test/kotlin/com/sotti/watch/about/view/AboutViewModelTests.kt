package com.sotti.watch.about.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sotti.watch.about.view.AboutViewModel.Companion.easterEggInteractionsThreshold
import com.sotti.watch.tests.common.CoroutinesTestRule
import com.sotti.watch.tests.common.getValueForTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.sotti.watch.about.view.AboutViewIntents.*
import com.sotti.watch.about.view.AboutViewActions.*

@ExperimentalCoroutinesApi
internal class AboutViewModelTests {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AboutViewModel

    @Before
    fun setUp() {
        viewModel = AboutViewModel()
    }

    @Test
    fun whenOpenGithub_triggersOpenGithubAction() {
        with(coroutinesTestRule.testDispatcher) {
            runBlockingTest {
                viewModel.onIntent(OnOpenGithubProfile)
                Assert.assertEquals(
                    viewModel.actions.getValueForTest(),
                    OpenSocialMediaProfile(githubUri)
                )
            }
        }
    }

    @Test
    fun whenOpenMedium_triggersOpenMediumAction() {
        with(coroutinesTestRule.testDispatcher) {
            runBlockingTest {
                viewModel.onIntent(OnOpenMediumProfile)
                Assert.assertEquals(
                    viewModel.actions.getValueForTest(),
                    OpenSocialMediaProfile(mediumUri)
                )
            }
        }
    }

    @Test
    fun whenOpenTwitter_triggersOpenTwitterAction() {
        with(coroutinesTestRule.testDispatcher) {
            runBlockingTest {
                viewModel.onIntent(OnOpenTwitterProfile)
                Assert.assertEquals(
                    viewModel.actions.getValueForTest(),
                    OpenSocialMediaProfile(twitterUri)
                )
            }
        }
    }

    @Test
    fun whenOpenLinkedIn_triggersOpenLinkedInAction() {
        with(coroutinesTestRule.testDispatcher) {
            runBlockingTest {
                viewModel.onIntent(OnOpenLinkedInProfile)
                Assert.assertEquals(
                    viewModel.actions.getValueForTest(),
                    OpenSocialMediaProfile(linkedInUri)
                )
            }
        }
    }

    @Test
    fun whenOpenStackOverflow_triggersOpenStackOverflowAction() {
        with(coroutinesTestRule.testDispatcher) {
            runBlockingTest {
                viewModel.onIntent(OnOpenStackOverflowProfile)
                Assert.assertEquals(
                    viewModel.actions.getValueForTest(),
                    OpenSocialMediaProfile(stackOverflowUri)
                )
            }
        }
    }

    @Test
    fun whenEasterEggDiscovered_triggersEasterEggAction() {
        with(coroutinesTestRule.testDispatcher) {
            runBlockingTest {
                repeat(easterEggInteractionsThreshold) {
                    viewModel.onIntent(OnShowEasterEgg)
                }
                Assert.assertEquals(
                    viewModel.actions.getValueForTest(),
                    ShowEasterEgg
                )
            }
        }
    }
}