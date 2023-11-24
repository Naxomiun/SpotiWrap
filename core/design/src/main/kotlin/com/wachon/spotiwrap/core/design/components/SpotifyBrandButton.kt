package com.wachon.spotiwrap.core.design.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wachon.spotiwrap.core.design.R
import com.wachon.spotiwrap.core.design.theme.Button
import com.wachon.spotiwrap.core.design.theme.SpotiWrapTheme
import com.wachon.spotiwrap.core.design.theme.SpotifyBlack
import com.wachon.spotiwrap.core.design.theme.SpotifyGreen

@Composable
fun SpotifyButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = SpotifyGreen,
            contentColor = SpotifyBlack
        ),
        shape = RoundedCornerShape(33),
        onClick = onClick,
        contentPadding = PaddingValues(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = text, style = Button)
            Spacer(modifier = Modifier.padding(PaddingValues(start = 5.dp)))
            Image(
                modifier = Modifier
                    .width(70.dp),
                painter = painterResource(id = R.drawable.ic_spotify_logo_full),
                contentDescription = null,
            )
        }
    }

}

@Preview
@Composable
fun PreviewSpotifyButton() {
    SpotiWrapTheme {
        SpotifyButton(text = "Clic para continuar", onClick = {})
    }
}