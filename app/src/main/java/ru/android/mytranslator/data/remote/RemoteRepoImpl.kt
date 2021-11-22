package ru.android.mytranslator.data.remote

import ru.android.mytranslator.DataModel
import ru.android.mytranslator.DataSource
import ru.android.mytranslator.Repository

class RemoteRepoImpl(
    private val dataSource: DataSource<List<DataModel>>,
) : Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}