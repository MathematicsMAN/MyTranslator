package ru.android.mytranslator.data

import io.reactivex.Observable
import ru.android.mytranslator.DataModel
import ru.android.mytranslator.DataSource

class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation = RetrofitImplementation()
) : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return remoteProvider.getData(word)
    }
}