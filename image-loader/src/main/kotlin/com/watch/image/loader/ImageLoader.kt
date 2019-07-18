package com.watch.image.loader

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sotti.ui.kit.pixelsToDp

internal fun ImageView.loadImage(
    imageUrl: String?,
    @DrawableRes placeHolderResId: Int,
    @DrawableRes errorImageResId: Int = placeHolderResId,
    roundedCorners: Boolean = false
) {
    val request = Glide
        .with(this)
        .load(imageUrl)
        .placeholder(placeHolderResId)
        .error(errorImageResId)

    if (roundedCorners) request.transform(
        MultiTransformation(
            CenterCrop(),
            RoundedCorners(
                resources.getDimension(R.dimen.corner_radius_large).pixelsToDp(context).toInt()
            )
        )
    )

    request.into(this)
}