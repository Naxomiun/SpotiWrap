package com.wachon.spotiwrap.features.login.presentation

import android.app.Activity
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import com.spotify.sdk.android.auth.LoginActivity
import com.wachon.spotiwrap.core.auth.scopes.AuthConfig
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    navigateToMenu: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        val response = AuthorizationClient.getResponse(it.resultCode, it.data)
        viewModel.handleLoginResponse(response, navigateToMenu)
    }

    LaunchedEffect(key1 = state.authConfig) {
        if (state.authConfig == null) return@LaunchedEffect
        launcher.launch(
            LoginActivity.getAuthIntent(
                context as Activity,
                getAuthenticationRequest(state.authConfig!!)
            )
        )
    }

    LoginContent(
        state = state,
        onLoginClicked = viewModel::login
    )

}

@Composable
fun LoginContent(
    state: LoginViewModel.State,
    modifier: Modifier = Modifier,
    onLoginClicked: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            modifier = Modifier
                .padding(16.dp),
            onClick = { onLoginClicked() })
        {
            Text(text = "Login")
        }
    }
}

private fun getAuthenticationRequest(authConfig: AuthConfig): AuthorizationRequest {
    return AuthorizationRequest
        .Builder(authConfig.clientId, AuthorizationResponse.Type.TOKEN, Uri.parse(authConfig.redirectUrl).toString())
        .setShowDialog(false)
        .setScopes(authConfig.getScopesAsTypedArray())
        .setCampaign(authConfig.campaign)
        .build()
}