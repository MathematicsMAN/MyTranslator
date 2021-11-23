package ru.android.mytranslator.ui

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.android.mytranslator.di.application
import ru.android.mytranslator.di.historyScreen
import ru.android.mytranslator.di.mainScreen

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}
