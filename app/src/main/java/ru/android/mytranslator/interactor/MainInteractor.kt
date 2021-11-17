package ru.android.mytranslator.interactor

import ru.android.mytranslator.AppState
import ru.android.mytranslator.DataModel
import ru.android.mytranslator.Interactor
import ru.android.mytranslator.Repository

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>,
) : Interactor<AppState> {

    override suspend fun getData(word: String, isRemoteSource: Boolean): AppState {
        return if (isRemoteSource) {
            val data = remoteRepository.getData(word)
            AppState.Success(data)
        } else {
            val data = localRepository.getData(word)
            AppState.Success(data)
        }
    }
}
