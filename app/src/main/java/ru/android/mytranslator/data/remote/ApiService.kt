package ru.android.mytranslator.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ru.android.mytranslator.DataModel

interface ApiService {
    @GET("words/search")
    suspend fun search(
        @Query("search") word: String
    ): List<DataModel>
}
