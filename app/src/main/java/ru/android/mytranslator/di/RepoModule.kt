package ru.android.mytranslator.di

import dagger.Module
import dagger.Provides
import ru.android.mytranslator.DataModel
import ru.android.mytranslator.DataSource
import ru.android.mytranslator.Repository
import ru.android.mytranslator.data.RetrofitImplementation
import ru.android.mytranslator.repository.RepoImpl
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepoModule {
    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    fun providesRemoteRepo(@Named(NAME_REMOTE) dataSource: DataSource<List<DataModel>>):
            Repository<List<DataModel>> {
        return RepoImpl(dataSource)
    }

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    fun providesLocalRepo(@Named(NAME_LOCAL) dataSource: DataSource<List<DataModel>>):
            Repository<List<DataModel>> {
        return RepoImpl(dataSource)
    }

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRemoteDataSource(): DataSource<List<DataModel>> {
        return RetrofitImplementation()
    }

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideLocalDataSource(): DataSource<List<DataModel>> {
        return RetrofitImplementation() // todo local repository
    }
}