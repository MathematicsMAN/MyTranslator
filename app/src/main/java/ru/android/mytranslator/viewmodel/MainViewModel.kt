package ru.android.mytranslator.viewmodel

import kotlinx.coroutines.*
import ru.android.models.AppState
import ru.android.mytranslator.interactor.MainInteractor

class MainViewModel(
    private val interactor: MainInteractor,
) : ru.android.base.BaseViewModel<AppState>() {

    private val viewModelScope = CoroutineScope(
        Dispatchers.Main + SupervisorJob()
    )

    fun getWordDescriptions(word: String, isOnline: Boolean) {
        viewModelScope.launch {
            try {
                val data = interactor.getData(word, isOnline)
                stateLiveData.value = data
            } catch (e: Exception) {
                stateLiveData.value = AppState.Error(e)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}