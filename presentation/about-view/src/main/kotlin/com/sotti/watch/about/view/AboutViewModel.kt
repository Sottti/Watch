package com.sotti.watch.about.view

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sotti.watch.about.view.AboutViewActions.OpenSocialMediaProfile
import com.sotti.watch.about.view.AboutViewActions.ShowEasterEgg
import com.sotti.watch.about.view.AboutViewIntents.*
import com.sotti.watch.utils.SingleLiveAction
import com.sotti.watch.utils.exhaustive

internal class AboutViewModel : ViewModel() {

    companion object {
        @VisibleForTesting
        internal const val easterEggInteractionsThreshold = 7
    }

    private var easterEggInteractionsCount = 0

    private val _actions = SingleLiveAction<AboutViewActions>()
    val actions: LiveData<AboutViewActions> = _actions

    fun onIntent(intent: AboutViewIntents) {
        when (intent) {
            OnShowEasterEgg -> {
                easterEggInteractionsCount += 1
                if (easterEggInteractionsCount >= easterEggInteractionsThreshold) {
                    easterEggInteractionsCount = 0
                    _actions.value = ShowEasterEgg
                } else Unit
            }
            OnOpenGithubProfile, OnOpenMediumProfile, OnOpenTwitterProfile, OnOpenLinkedInProfile,
            OnOpenStackOverflowProfile -> _actions.value = OpenSocialMediaProfile(getUrl(intent))
        }.exhaustive
    }


    private fun getUrl(intent: AboutViewIntents) = when (intent) {
        OnOpenGithubProfile -> githubUri
        OnOpenMediumProfile -> mediumUri
        OnOpenTwitterProfile -> twitterUri
        OnOpenLinkedInProfile -> linkedInUri
        OnOpenStackOverflowProfile -> stackOverflowUri
        OnShowEasterEgg -> throw IllegalArgumentException("The OnShowEasterEgg intent shouldn't reach this when statement")
    }.exhaustive
}