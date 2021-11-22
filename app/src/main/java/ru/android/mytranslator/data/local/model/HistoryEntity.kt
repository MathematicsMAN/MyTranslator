package ru.android.mytranslator.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import ru.android.mytranslator.AppState
import ru.android.mytranslator.DataModel

@Entity(
    indices = [Index(value = ["word"], unique = true)]
)
data class HistoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "word")
    val word: String,

    @ColumnInfo(name = "description")
    val description: String? = null
) {
    fun toDomainModel() = DataModel(text = word, meaning = null)
}

fun AppState.toEntity() = when(this) {
    is AppState.Success -> {
        val searchResult = this.data
        if (searchResult.isEmpty()) {
            null
        } else {
            HistoryEntity(
                word = searchResult.first().text.orEmpty(),
                description = searchResult.first().meaning?.joinToString()
            )
        }
    }
    else -> null
}