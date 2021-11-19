package ru.android.mytranslator.domain.calculator

import ru.android.mytranslator.domain.state.StopwatchState
import ru.android.mytranslator.domain.timestamp.TimestampProvider

class StopwatchStateCalculator(
    private val timestampProvider: TimestampProvider,
    private val elapsedTimeCalculator: ElapsedTimeCalculator
) {
    fun calculateRunningState(oldState: StopwatchState) : StopwatchState.Running =
        when (oldState) {
            is StopwatchState.Running -> oldState
            is StopwatchState.Paused -> {
                StopwatchState.Running(
                    startTime = timestampProvider.getMs(),
                    elapsedTime = oldState.elapsedTime
                )
            }
        }

    fun calculatePausedState(oldState: StopwatchState) : StopwatchState.Paused =
        when (oldState) {
            is StopwatchState.Running -> {
                val elapsedTime = elapsedTimeCalculator.calculate(oldState)
                StopwatchState.Paused(elapsedTime = elapsedTime)
            }
            is StopwatchState.Paused -> oldState
        }
}