package ru.android.mytranslator.di

import androidx.room.Room
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.android.data.local.HistoryDatabase
import ru.android.data.local.LocalRepoImpl
import ru.android.data.local.RoomDataSource
import ru.android.data.remote.RemoteRepoImpl
import ru.android.data.remote.RetrofitImplementation
import ru.android.history.interactor.HistoryInteractor
import ru.android.history.ui.HistoryActivity
import ru.android.history.ui.HistoryViewModel
import ru.android.models.*
import ru.android.mytranslator.interactor.MainInteractor
import ru.android.mytranslator.ui.activity.MainActivity
import ru.android.mytranslator.viewmodel.MainViewModel

val application = module {
    single {
        Room.databaseBuilder(get(), HistoryDatabase::class.java, "HistoryDB.db").build()
    }
    single { get<HistoryDatabase>().historyDao() }

    single<DataSource<List<DataModel>>> { RetrofitImplementation() }
    single<Repository<List<DataModel>>> { RemoteRepoImpl(get()) }

    single<DataSourceLocal<List<DataModel>>> { RoomDataSource(get()) }
    single<RepositoryLocal<List<DataModel>>> { LocalRepoImpl(get()) }
}

val mainScreen = module {
    scope(named<MainActivity>()) {
        scoped { MainInteractor(get(), get()) }
        viewModel { MainViewModel(get()) }
    }
}

val historyScreen = module {
    scope(named<HistoryActivity>()) {
        scoped<IHistoryInteractor> { HistoryInteractor(get()) }
        viewModel { HistoryViewModel(get()) }
    }
}