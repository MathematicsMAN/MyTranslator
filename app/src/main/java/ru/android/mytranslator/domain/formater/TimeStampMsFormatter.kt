package ru.android.mytranslator.domain.formater

class TimeStampMsFormatter {
    fun format(timestamp: Long): String {
        val msFormatted = (timestamp % 1000).toString().padStart(3, '0')

        val seconds = timestamp / 1000
        val secondsFormatted = (seconds % 60).toString().padStart(2, '0')

        val minutes = seconds / 60
        val minutesFormatted = (minutes % 60).toString().padStart(2, '0')

        val hours = minutes / 60

        return if (hours > 0) {
            val hoursFormatted = hours.toString()
            "$hoursFormatted:$minutesFormatted:$secondsFormatted"
        } else {
            "$minutesFormatted:$secondsFormatted.$msFormatted"
        }
    }

    companion object {
        const val DEFAULT_MS_FORMATTED = "00:00.000"
    }
}

