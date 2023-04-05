package com.wachon.spotiwrap.presentation.login

import android.app.Fragment
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse

class LoginFragment : Fragment() {

    companion object {
        private const val CLIENT_ID = "36ff2c568d2c46e5846de5475c4041d9"
        private const val REDIRECT_URI = "spotiwrap://auth"
        private const val AUTH_TOKEN_REQUEST_CODE = 0x10
        private const val AUTH_CODE_REQUEST_CODE = 0x11
    }

    var mAccessToken = mutableStateOf("")
    var mAccessCode = mutableStateOf("")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
        Log.d("oleole", "onCreateView: ")
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    private fun requestToken() {
        val request: AuthorizationRequest = getAuthenticationRequest(AuthorizationResponse.Type.TOKEN)
        AuthorizationClient.openLoginActivity(this.activity, AUTH_TOKEN_REQUEST_CODE, request)
    }

    private fun requestCode() {
        val request: AuthorizationRequest = getAuthenticationRequest(AuthorizationResponse.Type.CODE)
        AuthorizationClient.openLoginActivity(this.activity, AUTH_CODE_REQUEST_CODE, request)
    }

    private fun getAuthenticationRequest(type: AuthorizationResponse.Type): AuthorizationRequest {
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
}


