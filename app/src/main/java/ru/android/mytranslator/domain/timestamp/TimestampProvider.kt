package ru.android.mytranslator.domain.timestamp

interface TimestampProvider {

    fun getMs(): Long
}