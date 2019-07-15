package com.sotti.watch.about.view

import androidx.lifecycle.ViewModel
import com.sotti.watch.about.view.AboutViewActions.OpenSocialMediaProfile
import com.sotti.watch.about.view.AboutViewActions.ShowEasterEgg
import com.sotti.watch.about.view.AboutViewIntents.*
import com.sotti.watch.utils.ViewActions
import com.sotti.watch.utils.ViewActionsHandler

internal class AboutViewModel : ViewModel() {

    companion object {
        private const val easterEggInteractionsThreshold = 7
    }

    private var easterEggInteractionsCount = 0

    val actions = ViewActions<ViewActionsHandler<AboutViewActions>>()

    fun onIntent(intents: AboutViewIntents) = when (intents) {
        OnShowEasterEgg -> {
            easterEggInteractionsCount += 1
            if (easterEggInteractionsCount == easterEggInteractionsThreshold) {
                easterEggInteractionsCount = 0
                actions.new { handleAction(ShowEasterEgg) }
            } else Unit
        }
        OnOpenGithubProfile -> actions.new { handleAction(OpenSocialMediaProfile("https://github.com/Sottti")) }
        OnOpenMediumProfile -> actions.new { handleAction(OpenSocialMediaProfile("https://medium.com/@sotti")) }
        OnOpenTwitterProfile -> actions.new { handleAction(OpenSocialMediaProfile("https://twitter.com/Sotttti")) }
        OnOpenLinkedInProfile -> actions.new { handleAction(OpenSocialMediaProfile("https://uk.linkedin.com/in/sotti")) }
        OnOpenStackOverflowProfile -> actions.new { handleAction(OpenSocialMediaProfile("https://stackoverflow.com/users/1177959/sotti")) }
    }
}