package com.sotti.watch.intents

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

