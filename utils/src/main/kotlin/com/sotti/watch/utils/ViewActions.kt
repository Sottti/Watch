package com.sotti.watch.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class ViewActions<T> : SingleLiveAction<(T.() -> Unit)?>() {

    fun register(owner: LifecycleOwner, receiver: T) {
        observe(owner, Observer { action -> action?.run { receiver.action() } })
    }

    fun new(action: (T.() -> Unit)?) {
        value = action
    }
}