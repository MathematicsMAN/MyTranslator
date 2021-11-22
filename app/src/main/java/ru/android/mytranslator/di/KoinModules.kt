package ru.android.mytranslator.di

import androidx.room.Room
import org.koin.core.qualifier.named
import org.koin.core.scope.get
import org.koin.dsl.module
import ru.android.mytranslator.*
import ru.android.mytranslator.data.remote.RetrofitImplementation
import ru.android.mytranslator.data.local.HistoryDatabase
import ru.android.mytranslator.data.local.LocalRepoImpl
import ru.android.mytranslator.data.local.RoomDataSource
import ru.android.mytranslator.interactor.MainInteractor
import ru.android.mytranslator.data.remote.RemoteRepoImpl
import ru.android.mytranslator.interactor.history.HistoryInteractor
import ru.android.mytranslator.ui.history.HistoryViewModel
import ru.android.mytranslator.viewmodel.MainViewModel

val application = module {
    single {
        Room.databaseBuilder(
            get(),
            HistoryDatabase::class.java,
            "HistoryDB.db"
        ).build()
    }
    single { get<HistoryDatabase>().historyDao() }

    single<DataSource<List<DataModel>>>() {
        RetrofitImplementation()
    }

    single<Repository<List<DataModel>>>() {
        RemoteRepoImpl(get())
    }

    single<DataSourceLocal<List<DataModel>>>() {
        RoomDataSource(get())
    }

    single<RepositoryLocal<List<DataModel>>>() {
        LocalRepoImpl(get())
    }
}

val mainScreen = module {
    factory {
        MainInteractor(
            get(),
            get()
        )
    }
    factory { MainViewModel(get()) }
}

val historyScreen = module {
    factory { HistoryInteractor(get()) }
    factory { HistoryViewModel(get()) }
}