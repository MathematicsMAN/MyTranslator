package ru.android.mytranslator.viewmodel

import io.reactivex.observers.DisposableObserver
import ru.android.mytranslator.AppState
import ru.android.mytranslator.interactor.MainInteractor

class MainViewModel(
    private val interactor: MainInteractor,
) : BaseViewModel<AppState>() {

    fun getWordDescriptions(word: String, isOnline: Boolean) {
        compositeDisposable.add(interactor.getData(word, isOnline)
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.ui)
            .doOnSubscribe {
                stateLiveData.value = AppState.Loading(null)
            }
            .subscribeWith(getObservable())
        )
    }

    private fun getObservable(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(appState: AppState) {
                stateLiveData.value = appState
            }

            override fun onError(e: Throwable) {
                stateLiveData.value = AppState.Error(e)
            }

            override fun onComplete() = Unit
        }
    }
}