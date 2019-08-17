package com.sotti.watch.explore.view.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sotti.watch.explore.view.MovieOverViewUIMDecorator
import com.sotti.watch.explore.view.MovieOverviewUIM
import com.sotti.watch.explore.view.databinding.ExploreMovieVhBinding

internal sealed class ExploreItemVH(itemView: View) : RecyclerView.ViewHolder(itemView)

internal class ExploreMovieVH(
    private val viewBinding: ExploreMovieVhBinding,
    private val intentsListener: OnExploreItemIntentsListener

) : ExploreItemVH(viewBinding.root) {

    init {
        viewBinding.root.setOnClickListener { movieId?.let { intentsListener.showDetails(it) } }
    }

    private var movieId: Int? = null
    private var decorator: MovieOverViewUIMDecorator? = null

    fun onBind(movie: MovieOverviewUIM) {
        movieId = movie.id
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

    interface OnExploreItemIntentsListener {
        fun showDetails(movieId: Int)
    }
}