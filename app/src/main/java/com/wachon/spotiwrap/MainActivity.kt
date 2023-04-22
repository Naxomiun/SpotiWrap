package com.wachon.spotiwrap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.wachon.spotiwrap.core.design.theme.SpotiWrapTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SpotiWrapTheme {
                SpotiWrapNavHost()
            }
        }
    }
}