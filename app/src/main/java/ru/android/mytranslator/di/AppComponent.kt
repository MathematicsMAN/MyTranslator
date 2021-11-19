package ru.android.mytranslator.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import ru.android.mytranslator.ui.TranslatorApp
import javax.inject.Singleton

@Component(modules = [
    ActivityModule::class,
    InteractorModule::class,
    RepoModule::class,
    ViewModelModule::class,
    AndroidInjectionModule::class
])
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: TranslatorApp)
}