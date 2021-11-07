package ru.android.mytranslator.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.android.mytranslator.AppState
import ru.android.mytranslator.Presenter
import ru.android.mytranslator.View
import ru.android.mytranslator.data.DataSourceRemote
import ru.android.mytranslator.interactor.MainInteractor
import ru.android.mytranslator.repository.RepoImpl

class MainPresenter(
    private val interactor: MainInteractor = MainInteractor(
        RepoImpl(DataSourceRemote()),
        RepoImpl(DataSourceRemote())
    )
) : Presenter<AppState, View> {

    private val compositeDisposable = CompositeDisposable()
    private var currentView: View? = null

    override fun attachView(view: View) {
        if (currentView != view) {
            currentView = view
        }
    }

    override fun detachView(view: View) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(interactor.getData(word, isOnline)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { currentView?.renderData((AppState.Loading(null))) }
            .subscribe { currentView?.renderData(it) }
        )
    }
}