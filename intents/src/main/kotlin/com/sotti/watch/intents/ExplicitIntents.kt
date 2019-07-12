package com.sotti.watch.intents

import android.content.Intent
import android.net.Uri
import androidx.annotation.NonNull
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment

fun Fragment.loadChromeCustomTab(@NonNull url: String) {
    CustomTabsIntent.Builder()
        .setShowTitle(true)
        .build()
        .launchUrl(requireContext(), Uri.parse(url))
}

fun Fragment.openYouTubeVideo(videoKey: String) {
    requireContext()
        .startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=$videoKey")
            )
        )
}