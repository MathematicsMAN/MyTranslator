package ru.android.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.android.models.AppState

abstract class BaseActivity<T : AppState> : AppCompatActivity() {

    protected abstract val model: BaseViewModel<T>

    abstract fun renderData(appState: T)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model.getStateLiveData().observe(this) { renderData(it) }
    }
}