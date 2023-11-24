package com.wachon.spotiwrap.core.design.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wachon.spotiwrap.core.design.theme.SmallTitle
import com.wachon.spotiwrap.core.design.theme.SpotiWrapTheme
import com.wachon.spotiwrap.core.design.theme.SpotifyBlack

@Composable
fun ButtonDefault(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = SpotifyBlack
        ),
        shape = RoundedCornerShape(33),
        onClick = onClick,
        contentPadding = PaddingValues(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                style = SmallTitle,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.width(5.dp))

            Icon(
                modifier = Modifier.wrapContentSize(),
                imageVector = icon,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null,
            )
        }
    }

}

@Preview
@Composable
fun PreviewShareButton() {
    SpotiWrapTheme {
        ButtonDefault(text = "Check your Top", icon = Icons.Filled.PlayArrow, onClick = {})
    }
}