package com.wachon.spotiwrap.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginContent(
    token: String,
    onTokenRequested: () -> Unit,
    code: String,
    onCodeRequested: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = token)
        Button(modifier = Modifier.padding(16.dp), onClick = { onTokenRequested.invoke() }) {
            Text(text = "Access Token")
        }
        Text(text = code)
        Button(modifier = Modifier.padding(16.dp), onClick = { onCodeRequested.invoke() }) {
            Text(text = "Access Code")
        }
    }
}