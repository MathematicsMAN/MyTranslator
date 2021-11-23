package ru.android.mytranslator.data.local

import ru.android.mytranslator.AppState
import ru.android.mytranslator.DataModel
import ru.android.mytranslator.DataSourceLocal
import ru.android.mytranslator.RepositoryLocal

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