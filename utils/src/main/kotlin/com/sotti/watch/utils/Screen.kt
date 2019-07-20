package com.sotti.watch.utils

import android.content.Context
import android.util.DisplayMetrics


fun Float.dpToPixel(context: Context) =
    this * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)

fun Float.pixelsToDp(context: Context) =
    this / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)