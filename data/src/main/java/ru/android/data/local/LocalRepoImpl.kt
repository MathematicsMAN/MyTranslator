package ru.android.data.local

import ru.android.models.DataModel
import ru.android.models.AppState
import ru.android.models.DataSourceLocal
import ru.android.models.RepositoryLocal

class LocalRepoImpl(
    private val dataSource: DataSourceLocal<List<DataModel>>
) : RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveData(appState: AppState) {
        dataSource.saveData(appState)
    }
}