package com.wachon.spotiwrap.core.design.components

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.wachon.spotiwrap.core.common.R.string.get_spotify_for_free
import com.wachon.spotiwrap.core.common.R.string.open_in_spotify
import com.wachon.spotiwrap.core.design.R
import com.wachon.spotiwrap.core.design.theme.Button
import com.wachon.spotiwrap.core.design.theme.SpotiWrapTheme
import com.wachon.spotiwrap.core.design.theme.SpotifyGreenBackGround

@Composable
fun SpotifyOpenButton(
    url: String,
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 14.dp)
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = SpotifyGreenBackGround,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(33),
            onClick = {
                startActivity(
                    context,
                    Intent(Intent.ACTION_VIEW, Uri.parse(url)),
                    null
                )
            },
            contentPadding = PaddingValues(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            modifier = Modifier
                                .width(50.dp),
                            painter = painterResource(id = R.drawable.ic_spotify_white),
                            contentDescription = null,
                        )
                        TextNoPadding(
                            text = context.getString(
                                if (isSpotifyInstalled(context = context)
                                ) {
                                    open_in_spotify
                                } else {
                                    get_spotify_for_free
                                }
                            ),
                            style = Button.copy(fontSize = 14.sp)
                        )
                    }
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = url
                )
            }
        }
    }
}

fun isSpotifyInstalled(context: Context): Boolean {
    return try {
        context.packageManager.getApplicationInfo("com.spotify.music", 0)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}

@Preview
@Composable
fun PreviewSpotifyOpenButton() {
    SpotiWrapTheme {
        SpotifyOpenButton(url = "")
    }
}