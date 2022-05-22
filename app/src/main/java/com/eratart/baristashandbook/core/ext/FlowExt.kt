package com.eratart.baristashandbook.core.ext

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

fun <T> CoroutineScope.launchFlow(listener: suspend () -> Flow<T>) {
    this.launch { listener.invoke().launchIn(this) }
}

fun <T> Flow<T>.onNext(listener: suspend Flow<T>.(T) -> Unit): Flow<T> {
    return this.onEach { listener.invoke(this, it) }
}

fun <T> Flow<T>.applyBeforeAfter(actionStart: () -> Unit, actionEnd: () -> Unit): Flow<T> {
    return this.onStart { actionStart.invoke() }.onCompletion { actionEnd.invoke() }
}