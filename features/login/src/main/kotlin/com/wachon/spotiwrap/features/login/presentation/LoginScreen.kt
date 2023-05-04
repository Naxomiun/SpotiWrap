package com.wachon.spotiwrap.features.login.presentation

import android.app.Activity
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skydoves.landscapist.glide.GlideImage
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import com.spotify.sdk.android.auth.LoginActivity
import com.wachon.spotiwrap.core.auth.config.AuthConfig
import com.wachon.spotiwrap.core.design.components.collectEvents
import kotlinx.coroutines.flow.collectLatest
import com.wachon.spotiwrap.core.design.components.BrandContainer
import com.wachon.spotiwrap.core.design.theme.LargeTitle
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navigateToHome: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        val response = AuthorizationClient.getResponse(it.resultCode, it.data)
        viewModel.handleLoginResponse(response)
    }

    collectEvents {
        viewModel.event.collectLatest {
            when (it) {
                is LoginViewModel.Event.NavigateToHome -> navigateToHome()
                is LoginViewModel.Event.AuthConfigReceived -> {
                    launcher.launch(
                        LoginActivity.getAuthIntent(
                            context as Activity,
                            getAuthenticationRequest(it.authConfig)
                        )
                    )
                }
            }
        }
    }

    LoginContent(
        onLoginClicked = viewModel::login
    )

}

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    onLoginClicked: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        GlideImage(
            modifier = Modifier.fillMaxSize(),
            //TODO Find a more appropriate gif
            imageModel = { "https://media.giphy.com/media/Y0zTJ7VrKo9P2/giphy.gif" }
        )

        Row(
            modifier = Modifier
                .width(180.dp)
                .height(150.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp)
                .clickable { onLoginClicked() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            BrandContainer(
                modifier = Modifier.fillMaxSize(),
                backgroundColor = Color.White,
            ) {
                Text(
                    modifier = Modifier.fillMaxSize(),
                    text = "Login",
                    style = LargeTitle
                )
            }
        }
    }
}

private fun getAuthenticationRequest(authConfig: AuthConfig): AuthorizationRequest {
    return AuthorizationRequest
        .Builder(authConfig.clientId, AuthorizationResponse.Type.CODE, Uri.parse(authConfig.redirectUrl).toString())
        .setShowDialog(false)
        .setScopes(authConfig.getScopesAsTypedArray())
        .setCampaign(authConfig.campaign)
        .build()
}