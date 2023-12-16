package com.wachon.spotiwrap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.wachon.spotiwrap.ui.SpotiWRAPP

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        configSystemBars()
        super.onCreate(savedInstanceState)
        setContent { SpotiWRAPP() }
    }

    private fun configSystemBars() {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.Black.copy(alpha = 0.5f).toArgb()),
            navigationBarStyle = SystemBarStyle.dark(Color.Black.copy(alpha = 0.5f).toArgb())
        )
    }

}
