package ru.android.mytranslator.ui.description

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import ru.android.mytranslator.R
import ru.android.mytranslator.databinding.AcDescriptionBinding
import ru.android.mytranslator.ui.activity.isOnline
import java.lang.Exception

class DescriptionActivity : AppCompatActivity() {

    private val binding by lazy { AcDescriptionBinding.inflate(layoutInflater) }

    private val word by lazy { intent.extras?.getString(KEY_WORD).orEmpty() }
    private val description by lazy { intent.extras?.getString(KEY_DESCRIPTION).orEmpty() }
    private val imageUrl by lazy { intent.extras?.getString(KEY_IMAGE_URL) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setActionBarHomeButton()
        binding.root.setOnRefreshListener {
            startLoadingOrShowError()
        }
        binding.root.isRefreshing = true

        setData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setActionBarHomeButton() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setData() {
        binding.descriptionHeader.text = word
        binding.descriptionText.text = description
        val imageUrl = imageUrl
        if (imageUrl == null) {
            stopLoading()
        } else {
            usePicassoLoading(imageUrl)
        }
    }

    private fun startLoadingOrShowError() {
        if (isOnline(this)) {
            setData()
        } else {
            // todo alertDialog
            stopLoading()
        }
    }

    private fun stopLoading() {
        binding.root.isRefreshing = false
    }

    private fun useGlideLoading(imageUrl: String) {

    }

    private fun usePicassoLoading(imageUrl: String) {
        Picasso.get().load("https:$imageUrl")
            .placeholder(R.drawable.ic_launcher_foreground).fit().centerCrop()
            .into(binding.descriptionImage, object : Callback {
                override fun onSuccess() {
                    stopLoading()
                }

                override fun onError(e: Exception?) {
                    stopLoading()
                    binding.descriptionImage.setImageResource(R.drawable.ic_search)
                }
            })
    }

    companion object {

        private const val KEY_WORD = "KEY_WORD"
        private const val KEY_DESCRIPTION = "KEY_DESCRIPTION"
        private const val KEY_IMAGE_URL = "KEY_IMAGE_URL"

        fun getIntent(
            context: Context,
            word: String,
            description: String,
            imageUrl: String?
        ) = Intent(context, DescriptionActivity::class.java).apply {
            putExtra(KEY_WORD, word)
            putExtra(KEY_DESCRIPTION, description)
            putExtra(KEY_IMAGE_URL, imageUrl)
        }
    }
}