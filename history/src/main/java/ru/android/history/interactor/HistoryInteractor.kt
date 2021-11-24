package ru.android.history.interactor

import ru.android.models.DataModel
import ru.android.models.AppState
import ru.android.models.IHistoryInteractor
import ru.android.models.RepositoryLocal

class HistoryInteractor(
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : IHistoryInteractor {

    override suspend fun getData(): AppState {
        return AppState.Success(repositoryLocal.getData(""))
    }
}