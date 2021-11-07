package ru.android.mytranslator.data

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ru.android.mytranslator.DataModel

interface ApiService {
    @GET("words/search")
    fun search(
        @Query("search")
        word: String
    ): Observable<List<DataModel>>
}
