package ru.android.mytranslator.data.local

import ru.android.mytranslator.AppState
import ru.android.mytranslator.DataModel
import ru.android.mytranslator.DataSourceLocal
import ru.android.mytranslator.data.local.model.HistoryEntity
import ru.android.mytranslator.data.local.model.toEntity

class RoomDataSource(
    private val historyDao: HistoryDao,
) : DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return historyDao.all().map(HistoryEntity::toDomainModel)
    }

    override suspend fun saveData(appState: AppState) {
        val entity = appState.toEntity() ?: return
        historyDao.insert(entity)
    }
}