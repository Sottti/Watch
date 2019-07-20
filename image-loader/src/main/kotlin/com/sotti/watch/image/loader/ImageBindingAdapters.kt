package com.sotti.watch.image.loader

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.sotti.watch.image.loader.R

@BindingAdapter("posterPath")
fun loadPoster(imageView: ImageView, posterPath: String?) {
    if (posterPath == null) imageView.setImageResource(R.color.black_38)
    else with(imageView) {
        loadImage(
            imageUrl = posterPath.getPosterUrl(),
            placeHolderResId = R.color.black_6,
            roundedCorners = true
        )
    }
}

private fun String.getPosterUrl() = "https://image.tmdb.org/t/p/w500/${this}"