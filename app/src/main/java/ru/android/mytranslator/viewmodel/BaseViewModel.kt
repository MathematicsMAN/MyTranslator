package ru.android.mytranslator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.android.mytranslator.AppState
import ru.android.mytranslator.viewmodel.rx.ISchedulerProvider
import ru.android.mytranslator.viewmodel.rx.SchedulerProvider

abstract class BaseViewModel<T : AppState>(
    protected val stateLiveData: MutableLiveData<T> = MutableLiveData(),
    protected val schedulerProvider: ISchedulerProvider = SchedulerProvider(),
) : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    open fun getStateLiveData(): LiveData<T> = stateLiveData

    override fun onCleared() {
        compositeDisposable.clear()
    }

}