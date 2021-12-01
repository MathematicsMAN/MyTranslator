package ru.android.data.remote

import ru.android.models.DataModel
import ru.android.models.DataSource
import ru.android.models.Repository

class RemoteRepoImpl(
    private val dataSource: DataSource<List<DataModel>>,
) : Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}