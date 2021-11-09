package ru.android.mytranslator.di

import dagger.Module
import dagger.Provides
import ru.android.mytranslator.DataModel
import ru.android.mytranslator.Repository
import ru.android.mytranslator.interactor.MainInteractor
import javax.inject.Named

@Module
class InteractorModule {
    @Provides
    fun provideInteractor(
        @Named(NAME_REMOTE) remoteRepo: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) localRepo: Repository<List<DataModel>>
    ) = MainInteractor(remoteRepo, localRepo)
}