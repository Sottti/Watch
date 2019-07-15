package com.sotti.watch.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

fun View.spin() {
    animate()
        .rotation(360f)
        .setDuration(resources.getInteger(android.R.integer.config_mediumAnimTime).toLong())
        .setListener(
            object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    animate()
                        .rotation(0f)
                        .setListener(null)
                        .setDuration(0)
                        .start()
                }
            })
        .start()
}