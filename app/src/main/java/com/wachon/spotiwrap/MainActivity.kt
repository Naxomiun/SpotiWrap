package com.wachon.spotiwrap

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse.Type
import com.spotify.sdk.android.auth.AuthorizationResponse.Type.CODE
import com.spotify.sdk.android.auth.AuthorizationResponse.Type.TOKEN


class MainActivity : ComponentActivity() {

    val CLIENT_ID = "36ff2c568d2c46e5846de5475c4041d9"
    val REDIRECT_URI = "spotiwrap://auth"
    val AUTH_TOKEN_REQUEST_CODE = 0x10
    val AUTH_CODE_REQUEST_CODE = 0x11

    var mAccessToken = mutableStateOf("")
    var mAccessCode = mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    private fun requestCode() {
        val request: AuthorizationRequest = getAuthenticationRequest(CODE)
        AuthorizationClient.openLoginActivity(this, AUTH_CODE_REQUEST_CODE, request)
    }

    private fun requestToken() {
        val request: AuthorizationRequest = getAuthenticationRequest(TOKEN)
        AuthorizationClient.openLoginActivity(this, AUTH_TOKEN_REQUEST_CODE, request)
    }

    private fun getAuthenticationRequest(type: Type): AuthorizationRequest {
        return AuthorizationRequest.Builder(CLIENT_ID, type, Uri.parse(REDIRECT_URI).toString()).setShowDialog(false)
            .setScopes(arrayOf("user-read-email", "streaming")).setCampaign("c8a349442da94c2793d257bda6bc22c9").build()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val response = AuthorizationClient.getResponse(resultCode, data)

        if (AUTH_TOKEN_REQUEST_CODE == requestCode) {
            Log.d("oleole token", response.accessToken)
            mAccessToken.value = response.accessToken
        } else if (AUTH_CODE_REQUEST_CODE == requestCode) {
            Log.d("oleole code", response.code)
            mAccessCode.value = response.code
        }
    }

    @Composable
    fun MainScreen() {
        val token by mAccessToken
        val code by mAccessCode
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = token)
            Button(modifier = Modifier.padding(16.dp), onClick = { requestToken() }) {
                Text(text = "Access Token")
            }
            Text(text = code)
            Button(modifier = Modifier.padding(16.dp), onClick = { requestCode() }) {
                Text(text = "Access Code")
            }
        }
    }
}