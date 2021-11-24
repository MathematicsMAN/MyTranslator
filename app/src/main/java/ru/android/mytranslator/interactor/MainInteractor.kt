package ru.android.mytranslator.interactor

import ru.android.models.DataModel
import ru.android.models.AppState
import ru.android.models.Interactor
import ru.android.models.Repository
import ru.android.models.RepositoryLocal

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: RepositoryLocal<List<DataModel>>,
) : Interactor<AppState> {

    override suspend fun getData(word: String, isRemoteSource: Boolean): AppState {
        return if (isRemoteSource) {
            val data = remoteRepository.getData(word)
            val appState = AppState.Success(data)
            localRepository.saveData(appState)
            appState
        } else {
            val data = localRepository.getData(word)
            AppState.Success(data)
        }
    }
}
