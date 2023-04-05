package com.wachon.spotiwrap.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun Login() {

    val mAccessToken by remember { mutableStateOf("") }
    val mAccessCode by remember { mutableStateOf("") }

    LoginContent(token = mAccessToken, onTokenRequested = { /*TODO*/ }, code = mAccessCode, onCodeRequested = { /*TODO*/ })
}