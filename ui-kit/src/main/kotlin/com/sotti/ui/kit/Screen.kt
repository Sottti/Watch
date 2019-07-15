package com.sotti.ui.kit

import android.content.res.Resources

fun dpToPx(dps: Int) = (dps * Resources.getSystem().displayMetrics.density).toInt()
fun pxToDp(pixels: Int) = (pixels / Resources.getSystem().displayMetrics.density).toInt()