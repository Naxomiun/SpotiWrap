package com.wachon.spotiwrap.presentation.login

import android.app.Activity
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import com.spotify.sdk.android.auth.LoginActivity
import com.wachon.spotiwrap.presentation.login.Constants.REDIRECT_URI

object Constants {
    const val CLIENT_ID = "36ff2c568d2c46e5846de5475c4041d9"
    const val REDIRECT_URI = "spotiwrap://auth"
    const val AUTH_TOKEN_REQUEST_CODE = 0x10
    const val AUTH_CODE_REQUEST_CODE = 0x11
}

@Composable
fun Login(
    navigateToWrap: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {

    val context = LocalContext.current

    var mAccessToken by remember { mutableStateOf("") }
    val mAccessCode by remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {

        val response = AuthorizationClient.getResponse(it.resultCode, it.data)
        mAccessToken = response.accessToken
    }

    Surface(Modifier.fillMaxSize()) {
        LoginContent(
            token = mAccessToken,
            onTokenRequested = {
                launcher.launch(
                    LoginActivity.getAuthIntent(
                        context as Activity,
                        getAuthenticationRequest(AuthorizationResponse.Type.TOKEN)
                    )
                )
            },
            code = mAccessCode,
            onCodeRequested = { /*TODO*/ }
        )
    }


}

private fun getAuthenticationRequest(type: AuthorizationResponse.Type): AuthorizationRequest {
    return AuthorizationRequest
        .Builder(Constants.CLIENT_ID, type, Uri.parse(REDIRECT_URI).toString())
        .setShowDialog(false)
        .setScopes(arrayOf("user-read-email", "streaming"))
        .setCampaign("c8a349442da94c2793d257bda6bc22c9")
        .build()
}