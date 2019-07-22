package com.sotti.watch.utils

import android.util.TypedValue
import android.view.View
import java.io.BufferedReader

fun View.setSelectableItemBackground() {
    val outValue = TypedValue()
    context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
    setBackgroundResource(outValue.resourceId)
}

fun <T : Any> Class<T>.loadJsonAsText(path: String): String =
    getResourceAsStream(path)?.bufferedReader()?.use(BufferedReader::readText).orEmpty()
