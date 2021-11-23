package ru.android.mytranslator.ui.activity

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.getSystemService


fun isOnline(context: Context): Boolean {
    val connectivityManager = context.getSystemService<ConnectivityManager>()
    val networkInfo = connectivityManager?.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}