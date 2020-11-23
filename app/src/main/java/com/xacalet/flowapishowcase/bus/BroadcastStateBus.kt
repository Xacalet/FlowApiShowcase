package com.xacalet.flowapishowcase.bus

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class BroadcastStateBus<T>(initialValue: T) {

    private val _state = MutableStateFlow(initialValue)
    val state = _state.asStateFlow()
    val value = _state.value

    fun update(newValue: T) {
        _state.value = newValue
    }
}
