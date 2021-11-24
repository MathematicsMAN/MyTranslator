package ru.android.mytranslator.di

import androidx.room.Room
import org.koin.dsl.module
import ru.android.models.*
import ru.android.mytranslator.interactor.MainInteractor
import ru.android.history.interactor.HistoryInteractor
import ru.android.mytranslator.ui.history.HistoryViewModel
import ru.android.mytranslator.viewmodel.MainViewModel

val application = module {
    single {
        Room.databaseBuilder(
            get(),
            ru.android.data.local.HistoryDatabase::class.java,
            "HistoryDB.db"
        ).build()
    }
    single { get<ru.android.data.local.HistoryDatabase>().historyDao() }

    single<DataSource<List<DataModel>>> {
        ru.android.data.remote.RetrofitImplementation()
    }

    single<Repository<List<DataModel>>> {
        ru.android.data.remote.RemoteRepoImpl(get())
    }

    single<DataSourceLocal<List<DataModel>>> {
        ru.android.data.local.RoomDataSource(get())
    }

    single<RepositoryLocal<List<DataModel>>> {
        ru.android.data.local.LocalRepoImpl(get())
    }
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    factory { MainViewModel(get()) }
}

val historyScreen = module {
    factory<IHistoryInteractor> { HistoryInteractor(get()) }
    factory { HistoryViewModel(get()) }
}