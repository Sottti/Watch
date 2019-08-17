package com.sotti.watch.explore.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sotti.watch.explore.view.ExploreListItemUIM
import com.sotti.watch.explore.view.MovieOverviewUIM
import com.sotti.watch.explore.view.R
import com.sotti.watch.explore.view.databinding.ExploreMovieVhBinding
import com.sotti.watch.utils.exhaustive

internal class ExploreAdapter(private val intentsListener: ExploreMovieVH.OnExploreItemIntentsListener) :
    ListAdapter<ExploreListItemUIM,
            ExploreItemVH>(ExploreItemsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context).let { inflater ->
            when (viewType) {
                R.layout.explore_movie_vh ->
                    ExploreMovieVH(
                        ExploreMovieVhBinding.inflate(inflater, parent, false),
                        intentsListener
                    )
                else -> throw IllegalArgumentException("Couldn't create a view holder for viewType=$viewType")
            }
        }

    override fun onBindViewHolder(holder: ExploreItemVH, position: Int) {
        when (holder) {
            is ExploreMovieVH -> holder.onBind(getItem(position) as MovieOverviewUIM)
        }.exhaustive
    }

    override fun getItemViewType(position: Int) =
        when (getItem(position)) {
            is MovieOverviewUIM -> R.layout.explore_movie_vh
        }
}