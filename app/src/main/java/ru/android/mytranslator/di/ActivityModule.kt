package ru.android.mytranslator.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.android.mytranslator.ui.activity.MainActivity

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}