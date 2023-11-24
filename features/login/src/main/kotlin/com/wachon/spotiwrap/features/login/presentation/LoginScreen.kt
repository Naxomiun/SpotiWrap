package com.wachon.spotiwrap.features.login.presentation

import android.app.Activity
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import com.spotify.sdk.android.auth.LoginActivity
import com.wachon.spotiwrap.core.auth.config.AuthConfig
import com.wachon.spotiwrap.core.design.R
import com.wachon.spotiwrap.core.design.components.SpotifyButton
import com.wachon.spotiwrap.core.design.components.collectEvents
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.Title
import kotlinx.coroutines.flow.collectLatest
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
        AsyncImage(
            model = R.drawable.login_background,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.6F))
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1F))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                text = "Discover your top songs, artists and more!",
                style = Title.copy(fontSize = 29.sp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                text = "Create and enjoy new playlists based on your taste.",
                style = Body.copy(fontSize = 18.sp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6F)
            )

            SpotifyButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 24.dp)
                    .systemBarsPadding()
                    .height(60.dp),
                text = "Login with ",
                onClick = { onLoginClicked() },
            )
        }

    }
}

private fun getAuthenticationRequest(authConfig: AuthConfig): AuthorizationRequest {
    return AuthorizationRequest
        .Builder(
            authConfig.clientId,
            AuthorizationResponse.Type.CODE,
            Uri.parse(authConfig.redirectUrl).toString()
        )
        .setShowDialog(false)
        .setScopes(authConfig.getScopesAsTypedArray())
        .setCampaign(authConfig.campaign)
        .build()
}