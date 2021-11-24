package ru.android.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ru.android.models.DataModel

interface ApiService {
    @GET("words/search")
    suspend fun search(
        @Query("search") word: String
    ): List<DataModel>
}
