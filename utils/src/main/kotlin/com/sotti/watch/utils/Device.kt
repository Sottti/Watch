package com.sotti.watch.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import androidx.annotation.DimenRes

fun View.dimensToPx(@DimenRes dimensResId : Int)  = resources.getDimension(dimensResId).toInt()

fun Float.dpToPixel(context: Context) =
    this * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)

fun Float.pixelsToDp(context: Context) =
    this / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)