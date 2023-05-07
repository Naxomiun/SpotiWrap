package com.wachon.spotiwrap

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.wachon.spotiwrap.core.persistence.encrypted.CryptoManager
import com.wachon.spotiwrap.ui.SpotiWRAPP

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent { SpotiWRAPP() }
    }
}