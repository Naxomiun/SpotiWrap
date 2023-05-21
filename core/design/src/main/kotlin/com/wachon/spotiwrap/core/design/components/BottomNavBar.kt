package com.wachon.spotiwrap.core.design.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.wachon.spotiwrap.core.design.R
import com.wachon.spotiwrap.core.design.theme.SpotiWrapTheme
import com.wachon.spotiwrap.core.navigation.MainGraph
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    currentRoute: () -> String,
    onSelectedItem: (BottomNavBarItem) -> Unit,
    shouldShow: () -> Boolean
) {

    var firstVisibility by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        delay(600)
        firstVisibility = true
    }

    AnimatedVisibility(
        modifier = modifier
            .navigationBarsPadding()
            .padding(bottom = 24.dp),
        visible = shouldShow() && firstVisibility,
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
                .height(IntrinsicSize.Min)
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
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Surface(
            shadowElevation = 0.dp,
            color = MaterialTheme.colorScheme.surface,
            modifier = modifier
                .shadow(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(20)
                )
                .align(Alignment.Center)
        ) {
            Row(
                modifier = Modifier
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
    val primaryColor = MaterialTheme.colorScheme.primary

    val ripple = rememberRipple(bounded = false, color = primaryColor)

    val transition = updateTransition(targetState = isSelected, label = null)

    val iconColor by transition.animateColor(label = "") {
        if (it()) primaryColor else Color.White
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
            Image(
                painter = painterResource(id = navItem.icon),
                contentDescription = navItem.name,
                modifier = Modifier
                    .drawBehind {
                        drawCircle(
                            color = primaryColor,
                            alpha = dothAlpha,
                            radius = 2.3.dp.toPx(),
                            style = Fill,
                            center = Offset(size.width / 2, (size.height + 3.dp.toPx()) + ((1f - dothAlpha) * 48.dp.toPx()))
                        )
                    }.offset { IntOffset(x = 0, y = ((-dothAlpha)*10).roundToInt()) },
                colorFilter = ColorFilter.tint(iconColor),
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
            shouldShow = { true }
        )
    }
}

enum class BottomNavBarItem(val icon: Int) {
    Home(icon = R.drawable.ic_home),
    Top(icon = R.drawable.ic_top),
    Profile(icon = R.drawable.ic_profile);

    fun getScreenRoute() = when (this) {
        Home -> MainGraph.Home.route
        Top -> MainGraph.Top.route
        Profile -> MainGraph.Profile.route
    }

}