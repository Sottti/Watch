package com.sotti.watch.utils

import android.util.TypedValue
import android.view.View
import androidx.annotation.DimenRes

fun View.setSelectableItemBackground() {
    val outValue = TypedValue()
    context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
    setBackgroundResource(outValue.resourceId)
}
