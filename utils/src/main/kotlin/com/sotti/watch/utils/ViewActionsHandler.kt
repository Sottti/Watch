package com.sotti.watch.utils

interface ViewActionsHandler<T> {
    fun handleAction(action : T)
}