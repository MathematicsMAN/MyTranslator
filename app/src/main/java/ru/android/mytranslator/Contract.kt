package ru.android.mytranslator

//состояние одного экрана
sealed interface AppState {
    data class Success(val data: List<DataModel>) : AppState
    data class Error(val t: Throwable) : AppState
    data class Loading(val progress: Int? = null) : AppState
}

interface View {
    fun renderData(appState: AppState)
}

// Слой бизнес-логики
interface Interactor<T> {
   suspend fun getData(word: String, isRemoteSource: Boolean): T
}

// Получение и/или хранение данных для передачи интерактору
interface Repository<T> {
    suspend fun getData(word: String): T
}

// Источник данных для репозитория
interface DataSource<T> {
    suspend fun getData(word: String): T
}