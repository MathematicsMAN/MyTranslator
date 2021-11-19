package ru.android.mytranslator.repository

import ru.android.mytranslator.DataModel
import ru.android.mytranslator.DataSource
import ru.android.mytranslator.Repository

class RepoImpl(
    private val dataSource: DataSource<List<DataModel>>,
) : Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}