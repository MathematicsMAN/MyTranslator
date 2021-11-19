package ru.android.mytranslator.domain.state

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StopwatchOrchestrator(
    private val stopwatchStateHolder: StopwatchStateHolder,
    private val scope: CoroutineScope
) {

    private var job: Job? = null

    private val _ticker = MutableStateFlow("")
    val ticker: StateFlow<String> = _ticker

    fun start() {
        if(job == null) {
            startJob()
        }
        stopwatchStateHolder.start()
    }

    private fun startJob() {
        job = scope.launch {
            while (isActive) {
                _ticker.value = stopwatchStateHolder.toString()
                delay(20)
            }
        }
    }

    fun pause() {
        stopwatchStateHolder.pause()
        stopJob()
    }

    fun stop() {
        stopwatchStateHolder.stop()
        stopJob()
        _ticker.value = ""
    }

    private fun stopJob() {
//        scope.coroutineContext.cancelChildren()
        job?.cancel()
        job = null
    }
}