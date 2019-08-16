package com.sotti.watch.image.loader

import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.api.load

internal fun ImageView.loadImage(
    imageUrl: String?,
    @DrawableRes placeHolderResId: Int,
    @DrawableRes errorImageResId: Int = placeHolderResId,
    roundedCorners: Boolean = false
) {

    this.load(imageUrl) {
        crossfade(true)
        placeholder(placeHolderResId)
        error(errorImageResId)
    }

}