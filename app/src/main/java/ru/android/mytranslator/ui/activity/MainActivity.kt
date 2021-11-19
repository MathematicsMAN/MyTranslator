package ru.android.mytranslator.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.android.mytranslator.databinding.AcMainBinding
import ru.android.mytranslator.domain.calculator.ElapsedTimeCalculator
import ru.android.mytranslator.domain.calculator.StopwatchStateCalculator
import ru.android.mytranslator.domain.formater.TimeStampMsFormatter
import ru.android.mytranslator.domain.state.StopwatchOrchestrator
import ru.android.mytranslator.domain.state.StopwatchStateHolder
import ru.android.mytranslator.domain.timestamp.TimestampProvider

class MainActivity : AppCompatActivity() {

    private val binding by lazy { AcMainBinding.inflate(layoutInflater) }

    private val timestampProvider = object : TimestampProvider {
        override fun getMs(): Long {
            return System.currentTimeMillis()
        }
    }

    private val elapsedTimeCalculator = ElapsedTimeCalculator(timestampProvider)
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val stopwatchOrchestrator = StopwatchOrchestrator(
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestampProvider,
                elapsedTimeCalculator
            ),
            elapsedTimeCalculator,
            TimeStampMsFormatter()
        ),
        scope
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /* scope.launch {
            stopwatchOrchestrator.ticker
                .collect {
                    binding.textTime.text = it
                }
        }*/
        val liveData = stopwatchOrchestrator.ticker.asLiveData()
        liveData.observe(this) {
            binding.textTime.text = it
        }

        binding.startButton.setOnClickListener { stopwatchOrchestrator.start() }
        binding.stopButton.setOnClickListener { stopwatchOrchestrator.stop() }
        binding.pauseButton.setOnClickListener { stopwatchOrchestrator.pause() }
    }

    companion object {
        private val TAG = this::class.java.simpleName
    }
}