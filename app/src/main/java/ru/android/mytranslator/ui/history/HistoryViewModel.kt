package ru.android.mytranslator.ui.history

import android.util.Log
import kotlinx.coroutines.*
import ru.android.mytranslator.AppState
import ru.android.mytranslator.IHistoryInteractor
import ru.android.mytranslator.viewmodel.BaseViewModel

class HistoryViewModel(
    private val interactor: IHistoryInteractor,
) : BaseViewModel<AppState>() {

    private val viewModelScope = CoroutineScope(
        Dispatchers.Main + SupervisorJob()
                + CoroutineExceptionHandler { _, t ->
            Log.e(HistoryViewModel::class.java.simpleName, "error", t)
        }
    )

    init {
        getData()
    }

    fun getData() {
        stateLiveData.value = AppState.Loading(null)
        viewModelScope.coroutineContext.cancelChildren()
        viewModelScope.launch {
            val data = interactor.getData()
            stateLiveData.value = data
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancelChildren()
    }
}