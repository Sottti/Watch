/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sotti.watch.tests.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.common.truth.Truth
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.withTimeout
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Represents a list of capture values from a LiveData.
 *
 * This class is not threadsafe and must be used from the main thread.
 */
class LiveDataValueCapture<T> {

    private val _values = mutableListOf<T?>()
    val values: List<T?>
        get() = _values

    val channel = Channel<T?>(Channel.UNLIMITED)

    fun addValue(value: T?) {
        _values += value
        channel.offer(value)
    }

    suspend fun assertSendsValues(timeout: Long, vararg expected: T?) {
        val expectedList = expected.asList()
        if (values == expectedList) {
            return
        }
        try {
            withTimeout(timeout) {
                for (value in channel) {
                    if (values == expectedList) {
                        return@withTimeout
                    }
                }
            }
        } catch (ex: TimeoutCancellationException) {
            Truth.assertThat(values).isEqualTo(expectedList)
        }
    }
}

/**
 * Extension function to capture all values that are emitted to a LiveData<T> during the execution of
 * `captureBlock`.
 *
 * @param captureBlock a lambda that will
 */
inline fun <T> LiveData<T>.captureValues(block: LiveDataValueCapture<T>.() -> Unit) {
    val capture = LiveDataValueCapture<T>()
    val observer = Observer<T> {
        capture.addValue(it)
    }
    observeForever(observer)
    capture.block()
    removeObserver(observer)
}

/**
 * Get the current value from a LiveData without needing to register an observer.
 */
/*fun <T> LiveData<T>.getValueForTest(): T? {
    var value: T? = null
    var observer = Observer<T> {
        value = it
    }
    observeForever(observer)
    removeObserver(observer)
    return value
}*/

fun <T> LiveData<T>.getValueForTest(): T {
    val data = arrayOfNulls<Any>(1)
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data[0] = o
            latch.countDown()
            removeObserver(this)
        }
    }
    observeForever(observer)
    latch.await(2, TimeUnit.SECONDS)

    @Suppress("UNCHECKED_CAST")
    return data[0] as T
}