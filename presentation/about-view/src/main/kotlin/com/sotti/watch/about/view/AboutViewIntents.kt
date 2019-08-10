package com.sotti.watch.about.view

internal sealed class AboutViewIntents {
    object OnShowEasterEgg : AboutViewIntents()
    object OnOpenGithubProfile : AboutViewIntents()
    object OnOpenMediumProfile : AboutViewIntents()
    object OnOpenTwitterProfile : AboutViewIntents()
    object OnOpenLinkedInProfile : AboutViewIntents()
    object OnOpenStackOverflowProfile : AboutViewIntents()
}