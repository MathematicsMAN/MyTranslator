package ru.android.mytranslator.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.android.mytranslator.DataModel
import ru.android.mytranslator.DataSource

class RetrofitImplementation : DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return createRetrofit().create<ApiService>().search(word)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHtpClient())
            .build()
    }

    private fun createOkHtpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            )
            .build()
    }

    companion object {
        private const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"
    }

}
