package com.xacalet.flowapishowcase.bus

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow


class BroadcastEventBus<T> {

    private val _events = MutableSharedFlow<T>(REPLAY_COUNT)
    val events = _events.asSharedFlow()

    suspend fun postEvent(event: T) {
        _events.emit(event)
    }

    companion object {

        const val REPLAY_COUNT = 3
    }
}
