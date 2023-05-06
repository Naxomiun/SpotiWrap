package com.wachon.spotiwrap.core.design.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.wachon.spotiwrap.core.design.theme.SpotiWrapTheme
import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.*
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.theapache64.rebugger.Rebugger
import com.wachon.spotiwrap.core.design.theme.BubblegumPink
import com.wachon.spotiwrap.core.navigation.MainGraph

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    currentRoute: () -> String,
    onSelectedItem: (BottomNavBarItem) -> Unit,
) {

    Rebugger(
        trackMap = mapOf(
            "modifier" to modifier,
            "currentRoute" to currentRoute,
            "onSelectedItem" to onSelectedItem,
        ),
    )

    var visibility by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        visibility = true
    }

    AnimatedVisibility(
        visible = visibility,
        enter = slideInVertically(
            initialOffsetY = { 1000 },
            animationSpec = tween(
                durationMillis = 400,
                easing = LinearEasing
            )
        ),
        exit = slideOutVertically(
            targetOffsetY = { 1000 },
            animationSpec = tween(
                durationMillis = 400,
                easing = LinearEasing
            )
        )
    ) {
        BottomBarSurface(
            modifier = modifier
        ) {
            BottomNavBarItem.values().forEach {
                BottomBarButton(
                    navItem = it,
                    currentRoute = currentRoute,
                    onSelectedItem = onSelectedItem
                )
            }
        }
    }
}

@Composable
fun BottomBarSurface(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Surface(
            shadowElevation = 0.dp,
            color = MaterialTheme.colorScheme.surface,
            modifier = modifier
                .safeContentPadding()
                .padding(20.dp)
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .shadow(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(20)
                )
                .align(Alignment.Center)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                content()
            }
        }
    }
}

@Composable
fun BottomBarButton(
    modifier: Modifier = Modifier,
    navItem: BottomNavBarItem,
    currentRoute: () -> String,
    onSelectedItem: (BottomNavBarItem) -> Unit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val ripple = rememberRipple(bounded = false, color = BubblegumPink)
    val isSelected = currentRoute().contains(navItem.getScreenRoute())

    Box(
        modifier = modifier
            .padding(10.dp)
            .selectable(
                selected = isSelected,
                onClick = { onSelectedItem(navItem) },
                enabled = true,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = ripple
            ),
        contentAlignment = Alignment.Center
    ) {
        val iconColor by animateColorAsState(if (isSelected) BubblegumPink else Color.White, label = "")
        val buttonColor by animateColorAsState(if (isSelected) Color.Black else Color.Transparent, label = "")
        val buttonElevation by animateDpAsState(if (isSelected) 1.dp else 0.dp, label = "")

        Icon(
            navItem.icon,
            contentDescription = navItem.name,
            tint = iconColor
        )
    }
}


@Preview
@Composable
fun BottomNavBarPreview() {
    SpotiWrapTheme {
        BottomNavBar(
            currentRoute = { BottomNavBarItem.Home.getScreenRoute() },
            onSelectedItem = {}
        )
    }
}

enum class BottomNavBarItem(val icon: ImageVector) {
    Home(icon = Icons.Outlined.Home),
    Top(icon = Icons.Outlined.List),
    Profile(icon = Icons.Outlined.Face);

    fun getScreenRoute() = when (this) {
        Home -> MainGraph.Home.route
        Top -> MainGraph.Top.route
        Profile -> MainGraph.Profile.route
    }

}