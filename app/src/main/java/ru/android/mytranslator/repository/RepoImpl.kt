package ru.android.mytranslator.repository

import io.reactivex.Observable
import ru.android.mytranslator.DataModel
import ru.android.mytranslator.DataSource
import ru.android.mytranslator.Repository

class RepoImpl(
    private val dataSource: DataSource<List<DataModel>>
) : Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}