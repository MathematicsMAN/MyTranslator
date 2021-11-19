package ru.android.mytranslator.interactor

import io.reactivex.Observable
import ru.android.mytranslator.AppState
import ru.android.mytranslator.DataModel
import ru.android.mytranslator.Interactor
import ru.android.mytranslator.Repository

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>,
) : Interactor<AppState> {

    override fun getData(word: String, isRemoteSource: Boolean): Observable<AppState> {
        return if (isRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}
