package com.sotti.watch.app

import android.content.pm.ActivityInfo
import androidx.test.rule.ActivityTestRule
import com.sotti.watch.app.MainActivity

internal fun setLandscapeOrientation(activityTestRule: ActivityTestRule<MainActivity>) {
    activityTestRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
}

internal fun setPortraitOrientation(activityTestRule: ActivityTestRule<MainActivity>) {
    activityTestRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
}