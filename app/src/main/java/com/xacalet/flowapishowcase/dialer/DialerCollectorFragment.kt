package com.xacalet.flowapishowcase.dialer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.xacalet.flowapishowcase.R
import com.xacalet.flowapishowcase.bus.BroadcastEventBus
import com.xacalet.flowapishowcase.bus.BroadcastStateBus
import com.xacalet.flowapishowcase.databinding.FragmentDialerCollectorBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect

/**
 * A fragment that displays data collected from the provided [clickCountBus] and [numberStreamBus]
 * hot flows.
 */
class DialerCollectorFragment(
    private val clickCountBus: BroadcastStateBus<Int>,
    private val numberStreamBus: BroadcastEventBus<Int>
) : Fragment(R.layout.fragment_dialer_collector) {

    private lateinit var binding: FragmentDialerCollectorBinding

    private var clickCountBusJob: Job? = null

    private var numberStreamBusJob: Job? = null

    private var numberStream = mutableListOf<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDialerCollectorBinding.bind(view)

        with(binding) {
            clickCountSwitch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    clickCountBusJob?.cancel()
                    clickCountBusJob = lifecycleScope.launchWhenResumed {
                        clickCountBus.state.collect { updateClickCount(it) }
                    }
                } else {
                    clickCountBusJob?.cancel()
                }
            }
            numberStreamSwitch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    numberStreamBusJob?.cancel()
                    numberStreamBusJob = lifecycleScope.launchWhenResumed {
                        numberStreamBus.events.collect { number ->
                            numberStream.add(number)
                            if (numberStream.size > 20) {
                                numberStream.removeAt(0)
                            }
                            updateNumberStream(numberStream)
                        }
                    }
                } else {
                    numberStreamBusJob?.cancel()
                }
            }

            clickCountSwitch.isChecked = true
            numberStreamSwitch.isChecked = true
        }

        updateClickCount(clickCountBus.value)
    }

    private fun updateClickCount(value: Int) {
        binding.clickCountSwitch.text = "Clicks: $value"
    }

    private fun updateNumberStream(value: List<Int>) {
        binding.numberStreamSwitch.text = "Numbers: ${value.reversed().joinToString("")}"
    }
}
