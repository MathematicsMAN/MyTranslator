package ru.android.mytranslator.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import ru.android.mytranslator.viewmodel.MainViewModel
import kotlin.reflect.KClass

@Module(includes = [InteractorModule::class])
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModuleFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    //Добавление очередной ViewModel в Map, чтоб каждый раз вручную это не прописывать
    @Binds
    @ViewModelKey(MainViewModel::class)
    @IntoMap
    protected abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)