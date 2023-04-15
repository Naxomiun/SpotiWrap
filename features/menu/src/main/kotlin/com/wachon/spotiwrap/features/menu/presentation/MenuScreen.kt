package com.wachon.spotiwrap.features.menu.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.wachon.spotiwrap.features.menu.presentation.categories.artist.ArtistsContent
import com.wachon.spotiwrap.features.menu.presentation.categories.track.TracksContent
import org.koin.androidx.compose.koinViewModel

@Composable
fun MenuScreen(
    viewModel: MenuViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .windowInsetsPadding(
                WindowInsets.systemBars.only(WindowInsetsSides.Horizontal)
            )
    ) {
        Column(verticalArrangement = Arrangement.Top) {
            MenuTopAppBar(state = state)
            MenuContent(
                state = state,
                categories = state.categories,
                categorySelected = state.categorySelected,
                onCategorySelected = viewModel::onCategorySelected,
                onReachedEnd = {
                    //TODO increase the current offset the request the next list
                    viewModel.getTop(viewModel.getCategorySelected())
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun MenuTopAppBar(
    state: MenuViewModel.State
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                GlideImage(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    model = state.profile?.images?.first()?.url,
                    contentDescription = "perfil"
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = state.profile?.displayName.toString()
                )
            }
        }
    )
}

@Composable
fun MenuContent(
    state: MenuViewModel.State,
    categories: List<MenuCategory>,
    categorySelected: MenuCategory,
    onCategorySelected: (MenuCategory) -> Unit,
    onReachedEnd: () -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedIndex = categories.indexOfFirst { it == categorySelected }
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        MenuCategoryTabIndicator(
            Modifier.tabIndicatorOffset(tabPositions[selectedIndex])
        )
    }

    TabRow(
        selectedTabIndex = selectedIndex,
        indicator = indicator,
        modifier = modifier
    ) {
        categories.forEachIndexed { index, category ->
            Tab(
                selected = index == selectedIndex,
                onClick = { onCategorySelected(category) },
                text = {
                    Text(
                        text = when (category) {
                            MenuCategory.TRACKS -> MenuCategory.TRACKS.name
                            MenuCategory.ARTISTS -> MenuCategory.ARTISTS.name
                        },
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            )
        }
    }

    when (categorySelected) {
        MenuCategory.TRACKS -> {
            TracksContent(
                state = state,
                onReachedEnd = { onReachedEnd() },
                modifier = Modifier.fillMaxWidth()
            )
        }
        MenuCategory.ARTISTS -> {
            ArtistsContent(
                state = state,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun MenuCategoryTabIndicator(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface
) {
    Spacer(
        modifier
            .height(4.dp)
            .background(color, RoundedCornerShape(topStartPercent = 100, topEndPercent = 100))
    )
}