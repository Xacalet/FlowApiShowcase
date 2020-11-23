package com.xacalet.flowapishowcase.di

import com.xacalet.flowapishowcase.bus.BroadcastEventBus
import com.xacalet.flowapishowcase.bus.BroadcastStateBus
import com.xacalet.flowapishowcase.dialer.DialerCollectorFragment
import com.xacalet.flowapishowcase.dialer.DialerEmitterFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

var koinModule = module {

    fragment { DialerEmitterFragment(get(), get()) }
    fragment { DialerCollectorFragment(get(), get()) }

    single { BroadcastEventBus<String>() }
    single { BroadcastStateBus(0) }
}