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
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.theapache64.rebugger.Rebugger
import com.wachon.spotiwrap.core.design.theme.BubblegumPink
import com.wachon.spotiwrap.core.navigation.MainGraph
import kotlinx.coroutines.delay

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    currentRoute: () -> String,
    onSelectedItem: (BottomNavBarItem) -> Unit,
    shouldShow: Boolean,
) {

    Rebugger(
        trackMap = mapOf(
            "modifier" to modifier,
            "currentRoute" to currentRoute,
            "onSelectedItem" to onSelectedItem,
        ),
    )

    var firstVisibility by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        delay(600)
        firstVisibility = true
    }

    AnimatedVisibility(
        visible = shouldShow && firstVisibility,
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
                    isSelected = { currentRoute().contains(it.getScreenRoute()) },
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
    isSelected: () -> Boolean,
    onSelectedItem: (BottomNavBarItem) -> Unit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val ripple = rememberRipple(bounded = false, color = BubblegumPink)

    val transition = updateTransition(targetState = isSelected, label = null)

    val iconColor by transition.animateColor(label = "") {
        if (it()) BubblegumPink else Color.White
    }

    val dothAlpha by transition.animateFloat(label = "") {
        if (it()) 1f else 0f
    }

    Box(
        modifier = modifier
            .padding(10.dp)
            .selectable(
                selected = isSelected(),
                onClick = { onSelectedItem(navItem) },
                enabled = true,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = ripple
            ),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                navItem.icon,
                contentDescription = navItem.name,
                modifier = Modifier.drawBehind {
                    drawCircle(
                        color = BubblegumPink,
                        alpha = dothAlpha,
                        radius = 2.3.dp.toPx(),
                        style = Fill,
                        center = Offset(size.width/2,  (size.height + 3.dp.toPx()) + ((1f - dothAlpha) * 48.dp.toPx()))
                    )
                },
                tint = iconColor
            )

        }
    }
}


@Preview
@Composable
fun BottomNavBarPreview() {
    SpotiWrapTheme {
        BottomNavBar(
            currentRoute = { BottomNavBarItem.Home.getScreenRoute() },
            onSelectedItem = {},
            shouldShow = true
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