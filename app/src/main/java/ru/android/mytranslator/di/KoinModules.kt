package ru.android.mytranslator.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.android.mytranslator.DataModel
import ru.android.mytranslator.DataSource
import ru.android.mytranslator.Repository
import ru.android.mytranslator.data.RetrofitImplementation
import ru.android.mytranslator.interactor.MainInteractor
import ru.android.mytranslator.repository.RepoImpl
import ru.android.mytranslator.viewmodel.MainViewModel

val application = module {
    single<DataSource<List<DataModel>>>(named(NAME_REMOTE)) {
        RetrofitImplementation()
    }

    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) {
        RepoImpl(get(named(NAME_REMOTE)))
    }

    single<DataSource<List<DataModel>>>(named(NAME_LOCAL)) {
        RetrofitImplementation() // todo local
    }

    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) {
        RepoImpl(get(named(NAME_LOCAL))) // todo local
    }
}

val mainScreen = module {
    factory {
        MainInteractor(
            get(named(NAME_REMOTE)),
            get(named(NAME_LOCAL))
        )
    }
    factory { MainViewModel(get()) }
}