package com.sotti.watch.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

fun <T : ViewDataBinding> Fragment.inflate(
    @LayoutRes layoutResId: Int,
    container: ViewGroup?
): T = DataBindingUtil.inflate(layoutInflater, layoutResId, container, false)

fun <T : ViewDataBinding> ViewGroup.inflate(@LayoutRes layoutResId: Int): T =
    DataBindingUtil.inflate(
        LayoutInflater.from(context),
        layoutResId,
        this,
        true
    )

fun ViewGroup.inflate(@LayoutRes layoutResId: Int) {
    LayoutInflater.from(context).inflate(layoutResId, this)
}