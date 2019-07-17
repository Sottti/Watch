package com.watch.movies.data.datasources

import com.sotti.watch.utils.mapToSet
import com.watch.movies.domain.MovieOverviewDM

internal fun Set<MovieOverviewAM>.toDM() = mapToSet { it.toDM() }

internal fun MovieOverviewAM.toDM() = MovieOverviewDM(id, title, posterPath, voteAverage)