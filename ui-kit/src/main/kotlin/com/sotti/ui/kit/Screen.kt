package com.sotti.ui.kit

import android.content.Context
import android.content.res.Resources
import androidx.annotation.DimenRes

fun dpToPx(dps: Int) = (dps * Resources.getSystem().displayMetrics.density).toInt()

fun dpToPx(context: Context, @DimenRes resId: Int) = dpToPx(context.resources.getDimension(resId).toInt())

fun pxToDp(pixels: Int) = (pixels / Resources.getSystem().displayMetrics.density).toInt()