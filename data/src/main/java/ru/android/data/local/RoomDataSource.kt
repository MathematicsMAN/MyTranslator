package ru.android.data.local

import ru.android.models.DataModel
import ru.android.models.AppState
import ru.android.models.DataSourceLocal
import ru.android.data.local.model.HistoryEntity
import ru.android.data.local.model.toEntity

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