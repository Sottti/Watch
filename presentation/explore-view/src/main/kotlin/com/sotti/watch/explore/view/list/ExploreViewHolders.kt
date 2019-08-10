package com.sotti.watch.explore.view.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sotti.watch.explore.view.MovieOverViewUIMDecorator
import com.sotti.watch.explore.view.MovieOverviewUIM
import com.sotti.watch.explore.view.databinding.ExploreMovieVhBinding

internal sealed class ExploreItemVH(itemView: View) : RecyclerView.ViewHolder(itemView)

internal class ExploreMovieVH(
    private val viewBinding: ExploreMovieVhBinding
) : ExploreItemVH(viewBinding.root) {

    private var decorator: MovieOverViewUIMDecorator? = null

    fun onBind(movie: MovieOverviewUIM) {
        with(viewBinding) {
            uiModel = recycleDecorator(movie)
            executePendingBindings()
        }
    }

    private fun recycleDecorator(movie: MovieOverviewUIM) =
        with(decorator) {
            this?.bind(movie) ?: MovieOverViewUIMDecorator(
                movie,
                itemView.context
            ).also { decorator = it }
        }

}