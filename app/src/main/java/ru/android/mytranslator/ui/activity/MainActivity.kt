package ru.android.mytranslator.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.android.mytranslator.databinding.AcMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { AcMainBinding.inflate(layoutInflater) }

    private val stateFlow = MutableStateFlow(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.Main).launch {
//            stateFlow.collect {
//                Log.d(TAG, "Получили значение: $it")
//            }
        }

        binding.flowButton.setOnClickListener {
            stateFlow.value = stateFlow.value + 1
            Log.d(TAG, "обновили значение StateFlow: ${stateFlow.value}")
        }

    }

    companion object {
        private val TAG = this::class.java.simpleName
    }
}