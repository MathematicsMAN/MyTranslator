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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val flow = getFlow()
        val flow2 = getFlow2()
        binding.flowButton.setOnClickListener {
            startFlow(flow, flow2)
        }
    }

    private fun getFlow(): Flow<Int> = flow {
        Log.d(TAG, "start flow")
        (1..10).forEach {
            delay(300)
            Log.d(TAG, "Flow отдаёт значение $it")
            emit(it)
        }
    }

    private fun getFlow2() = flow {
        Log.d(TAG, "start flow2")
        ('a'..'t').forEach {
            delay(100)
            Log.d(TAG, "Flow2 отдаёт значение $it")
            emit(it)
        }
    }

    private fun startFlow(flow: Flow<Int>, flow2: Flow<Char>) = CoroutineScope(Dispatchers.Main).launch {
        flow
            .flowOn(Dispatchers.IO)
            .map { it * 3 }
            .filter { it % 2 == 0 }
            .zip(flow2) { f1, f2 -> "$f1 $f2" }
            .collect {
                Log.d(TAG, "Activity получила значение flow: $it")
            }
    }

    companion object {
        private val TAG = this::class.java.simpleName
    }
}