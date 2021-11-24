package ru.android.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.android.models.AppState

abstract class BaseViewModel<T : AppState>(
    protected val stateLiveData: MutableLiveData<T> = MutableLiveData(),
) : ViewModel() {

    open fun getStateLiveData(): LiveData<T> = stateLiveData

    override fun onCleared() {
    }

}