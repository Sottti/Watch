package com.sotti.watch.android.tests.common

import android.content.pm.ActivityInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario

fun <T : Fragment> FragmentScenario<T>.asPortrait() {
    onFragment {
        it.activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}

fun <T : Fragment> FragmentScenario<T>.asLandscape() {
    onFragment {
        it.activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }
}