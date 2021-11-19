package ru.android.mytranslator.domain.state

import ru.android.mytranslator.domain.calculator.ElapsedTimeCalculator
import ru.android.mytranslator.domain.calculator.StopwatchStateCalculator
import ru.android.mytranslator.domain.formater.TimeStampMsFormatter

class StopwatchStateHolder(
    private val stopwatchStateCalculator: StopwatchStateCalculator,
    private val elapsedTimeCalculator: ElapsedTimeCalculator,
    private val timeStampMsFormatter: TimeStampMsFormatter
) {
    var currentState: StopwatchState = StopwatchState.Paused(0)
        private set

    fun start() {
        currentState = stopwatchStateCalculator.calculateRunningState(currentState)
    }

    fun pause() {
        currentState = stopwatchStateCalculator.calculatePausedState(currentState)
    }

    fun stop() {
        currentState = StopwatchState.Paused(0)
    }

    override fun toString(): String {
        val elapsedTime = when(val currentState = currentState) {
            is StopwatchState.Paused -> currentState.elapsedTime
            is StopwatchState.Running -> elapsedTimeCalculator.calculate(currentState)
        }
        return timeStampMsFormatter.format(elapsedTime)
    }
}