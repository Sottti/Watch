package com.sotti.watch.explore.view.list

import androidx.recyclerview.widget.DiffUtil
import com.sotti.watch.explore.view.ExploreListItemUIM
import com.sotti.watch.explore.view.MovieOverviewUIM

internal class ExploreItemsDiffCallback : DiffUtil.ItemCallback<ExploreListItemUIM>() {

    override fun areItemsTheSame(oldItem: ExploreListItemUIM, newItem: ExploreListItemUIM) =
        if (oldItem::class != newItem::class) false
        else when (oldItem) {
            is MovieOverviewUIM -> oldItem.id == (newItem as MovieOverviewUIM).id
        }

    @Suppress("USELESS_CAST")
    override fun areContentsTheSame(oldItem: ExploreListItemUIM, newItem: ExploreListItemUIM) =
        when (oldItem) {
            is MovieOverviewUIM -> (oldItem as MovieOverviewUIM) == (newItem as MovieOverviewUIM)
        }
}