package com.sotti.watch.movies.data.datasources

import com.sotti.watch.movies.domain.MovieOverviewDM
import com.sotti.watch.utils.mapToSet

internal fun Set<MovieOverviewAM>.toDM() = mapToSet { it.toDM() }

internal fun MovieOverviewAM.toDM() = MovieOverviewDM(
    id = id,
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage,
    overview = overview
)