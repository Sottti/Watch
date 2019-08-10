package com.sotti.watch.about.view

internal sealed class AboutViewActions {
    data class OpenSocialMediaProfile(val url: String) : AboutViewActions()
    object ShowEasterEgg : AboutViewActions()
}