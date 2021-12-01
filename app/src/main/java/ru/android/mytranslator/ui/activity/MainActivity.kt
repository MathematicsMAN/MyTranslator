package ru.android.mytranslator.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import ru.android.history.ui.HistoryActivity
import ru.android.models.AppState
import ru.android.models.View
import ru.android.mytranslator.R
import ru.android.mytranslator.ui.MainAdapter
import ru.android.mytranslator.ui.SearchDialogFragment
import ru.android.mytranslator.ui.description.DescriptionActivity
import ru.android.mytranslator.ui.viewById
import ru.android.mytranslator.viewmodel.MainViewModel

class MainActivity : ru.android.base.BaseActivity<AppState>(), View {

    //    private lateinit var binding: AcMainBinding
    private var adapter: MainAdapter? = null

    private val searchFab by viewById<FloatingActionButton>(R.id.search_fab)

    private val mainActivityScope =
        getKoin().createScope("MainActivityScope", named<MainActivity>())

    override val model: MainViewModel by mainActivityScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.ac_main)
        searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    model.getWordDescriptions(searchWord, true)
                }
            })
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }

        val recycler by viewById<RecyclerView>(R.id.main_activity_recyclerview)
        recycler.layoutManager = LinearLayoutManager(applicationContext)
        adapter = MainAdapter { dataModel ->
            startActivity(
                DescriptionActivity.getIntent(
                    this,
                    word = dataModel.text.orEmpty(),
                    description = dataModel.meaning?.joinToString {
                        it.translation?.translation.orEmpty()
                    }.orEmpty(),
                    imageUrl = dataModel.meaning?.firstOrNull()?.imageUrl
                )
            )
        }
        recycler.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.historyItem -> {
                startActivity(HistoryActivity.createIntent(this))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivityScope.close()
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        val recycler by viewById<RecyclerView>(R.id.main_activity_recyclerview)
                        recycler.layoutManager =
                            LinearLayoutManager(applicationContext)
                        adapter = MainAdapter { }
                        recycler.adapter = adapter
                    } else {
                        adapter!!.submitList(dataModel)
                    }
                }
            }
            is AppState.Loading -> {
                val progressBarHorizontal by viewById<ProgressBar>(R.id.progress_bar_horizontal)
                val progressBarRound by viewById<ProgressBar>(R.id.progress_bar_round)
                showViewLoading()
                if (appState.progress != null) {
                    progressBarHorizontal.visibility = VISIBLE
                    progressBarRound.visibility = GONE
                    progressBarHorizontal.progress = appState.progress!!
                } else {
                    progressBarHorizontal.visibility = GONE
                    progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.t.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        val errorTextview by viewById<TextView>(R.id.error_textview)
        val reloadButton by viewById<Button>(R.id.reload_button)

        showViewError()
        errorTextview.text = error ?: getString(R.string.undefined_error)
        reloadButton.setOnClickListener {
            model.getWordDescriptions("hi", true)
        }
    }

    private fun showViewSuccess() {
        val successLinearLayout by viewById<FrameLayout>(R.id.success_linear_layout)
        val loadingFrameLayout by viewById<FrameLayout>(R.id.loading_frame_layout)
        val errorLinearLayout by viewById<FrameLayout>(R.id.error_linear_layout)

        successLinearLayout.visibility = VISIBLE
        loadingFrameLayout.visibility = GONE
        errorLinearLayout.visibility = GONE
    }

    private fun showViewLoading() {
        val successLinearLayout by viewById<FrameLayout>(R.id.success_linear_layout)
        val loadingFrameLayout by viewById<FrameLayout>(R.id.loading_frame_layout)
        val errorLinearLayout by viewById<FrameLayout>(R.id.error_linear_layout)

        successLinearLayout.visibility = GONE
        loadingFrameLayout.visibility = VISIBLE
        errorLinearLayout.visibility = GONE
    }

    private fun showViewError() {
        val successLinearLayout by viewById<FrameLayout>(R.id.success_linear_layout)
        val loadingFrameLayout by viewById<FrameLayout>(R.id.loading_frame_layout)
        val errorLinearLayout by viewById<FrameLayout>(R.id.error_linear_layout)

        successLinearLayout.visibility = GONE
        loadingFrameLayout.visibility = GONE
        errorLinearLayout.visibility = VISIBLE
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}