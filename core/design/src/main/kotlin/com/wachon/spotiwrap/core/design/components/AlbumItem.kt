package com.wachon.spotiwrap.core.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.BubblegumPink
import com.wachon.spotiwrap.core.design.theme.SubBody
import com.wachon.spotiwrap.core.design.ui.TopItemUI

@Composable
fun AlbumItem(item: TopItemUI, hideIcon: Boolean = false) {
    Column(
        modifier = Modifier
            .padding(PaddingValues(top = 4.dp, bottom = 4.dp))
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .padding(PaddingValues(4.dp)),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier.width(4.dp))

            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = item.first,
                    style = Body.copy(fontWeight = FontWeight.W600),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (item.second.isNotBlank()) {
                    Text(
                        text = item.second,
                        style = SubBody.copy(fontSize = 12.sp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                modifier = Modifier.weight(0.18F),
                text = item.third,
                textAlign = TextAlign.Center,
                style = SubBody.copy(fontSize = 12.sp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = BubblegumPink,
                modifier = Modifier
                    .size(25.dp)
                    .alpha(if (hideIcon) 0f else 1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

        }
    }
}

@Preview
@Composable
fun AlbumItemPreview() {
    AlbumItem(
        item = TopItemUI(id = "", image = "", first = "Candy Glaze", second = "PLUTO")
    )
}