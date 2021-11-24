package ru.android.models

import com.google.gson.annotations.SerializedName

/** Модель данных  */
data class DataModel(
    @SerializedName("text")
    val text: String?,
    @SerializedName("meanings")
    val meaning: List<Meaning>?
)

/** Значение флова/фразы */
data class Meaning(
    @SerializedName("translation")
    val translation: Translation?,
    @SerializedName("imageUrl")
    val imageUrl: String?
)

/** Перевод слова/фразы */
data class Translation(
    @SerializedName("text")
    val translation: String?
)
