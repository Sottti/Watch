package com.sotti.watch.utils

inline fun <T, R> Iterable<T>.mapToSet(transform: (T) -> R): Set<R> = mapTo(HashSet(), transform)