package ru.android.history.ui

import android.util.Log
import kotlinx.coroutines.*
import ru.android.base.BaseViewModel
import ru.android.models.AppState
import ru.android.models.IHistoryInteractor

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