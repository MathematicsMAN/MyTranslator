package ru.android.mytranslator.domain.calculator

import ru.android.mytranslator.domain.state.StopwatchState
import ru.android.mytranslator.domain.timestamp.TimestampProvider

class ElapsedTimeCalculator(
    private val timestampProvider: TimestampProvider
) {
    fun calculate(state: StopwatchState.Running): Long {
        val currentTimestamp = timestampProvider.getMs()
        val timePassedSinceStart = if (currentTimestamp > state.startTime) {
            currentTimestamp - state.startTime
        } else {
            0
        }
        return timePassedSinceStart + state.elapsedTime
    }
}