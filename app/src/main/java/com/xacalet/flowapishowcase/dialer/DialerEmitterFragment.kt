package com.xacalet.flowapishowcase.dialer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.xacalet.flowapishowcase.R
import com.xacalet.flowapishowcase.bus.BroadcastEventBus
import com.xacalet.flowapishowcase.bus.BroadcastStateBus
import com.xacalet.flowapishowcase.databinding.FragmentDialerEmitterBinding
import kotlinx.coroutines.launch


/**
 * A fragment that emits data through the provided [clickCountBus] and [numberStreamBus] hot flows.
 */
class DialerEmitterFragment(
    private val clickCountBus: BroadcastStateBus<Int>,
    private val numberStreamBus: BroadcastEventBus<Int>
) : Fragment(R.layout.fragment_dialer_emitter) {

    private lateinit var binding: FragmentDialerEmitterBinding

    private var clickerButtonCount = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDialerEmitterBinding.bind(view)

        with(binding) {
            button1.setOnClickListener { emitNumber(1) }
            button2.setOnClickListener { emitNumber(2) }
            button3.setOnClickListener { emitNumber(3) }
            button4.setOnClickListener { emitNumber(4) }
            button5.setOnClickListener { emitNumber(5) }
            button6.setOnClickListener { emitNumber(6) }
            button7.setOnClickListener { emitNumber(7) }
            button8.setOnClickListener { emitNumber(8) }
            button9.setOnClickListener { emitNumber(9) }
            button0.setOnClickListener { emitNumber(0) }
            clickerButton.setOnClickListener {
                clickerButtonCount++
                clickerButton.text = clickerButtonCount.toString()
                clickCountBus.update(clickerButtonCount)
            }
            updateReplyBuffer()
        }
    }

    private fun emitNumber(number: Int) {
        lifecycleScope.launch {
            numberStreamBus.postEvent(number)
            updateReplyBuffer()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateReplyBuffer() {
        val replyCount = BroadcastEventBus.REPLAY_COUNT
        val replyCache = numberStreamBus.events.replayCache.reversed().joinToString("")
        binding.replayConfigText.text = "Reply cache[$replyCount] = $replyCache"
    }
}
