package com.wachon.spotiwrap.features.top.presentation.components

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.wachon.spotiwrap.core.common.extensions.toShow
import com.wachon.spotiwrap.core.common.model.TopItemType
import com.wachon.spotiwrap.core.common.model.TopItemType.ARTISTS
import com.wachon.spotiwrap.core.common.model.TopItemType.TRACKS
import com.wachon.spotiwrap.core.design.components.NoRippleTheme
import com.wachon.spotiwrap.core.design.theme.BubblegumPink
import com.wachon.spotiwrap.core.design.theme.Title

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypesTabs(onTypeSelected: (TopItemType) -> Unit) {
    var selectedTime by remember { mutableIntStateOf(0) }
    val types = listOf(TRACKS, ARTISTS)



    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        SecondaryTabRow(
            containerColor = Color.Black,
            contentColor = Color.Black,
            selectedTabIndex = selectedTime
        ) {
            types.forEachIndexed { index, type ->
                Tab(
                    selected = selectedTime == index,
                    onClick = {
                        selectedTime = index
                        onTypeSelected.invoke(type)
                    },
                    text = {
                        val textColor: Color by animateColorAsState(
                            if (selectedTime == index) BubblegumPink else MaterialTheme.colorScheme.onBackground,
                            animationSpec = tween(250, easing = LinearEasing), label = ""
                        )

                        Text(
                            text = type.toShow(),
                            color = textColor,
                            style = Title.copy(fontSize = 13.sp, fontWeight = FontWeight.W600),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                )
            }
        }
    }
}