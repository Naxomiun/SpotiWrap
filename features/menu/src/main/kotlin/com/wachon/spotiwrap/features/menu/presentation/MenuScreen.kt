package com.wachon.spotiwrap.features.menu.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wachon.spotiwrap.features.artists.presentation.topartists.TopArtistsScreen
import com.wachon.spotiwrap.features.artists.presentation.topartists.TopArtistsViewModel
import com.wachon.spotiwrap.features.profile.presentation.ProfileTopBar
import com.wachon.spotiwrap.features.tracks.presentation.toptracks.TopTracksScreen
import com.wachon.spotiwrap.features.tracks.presentation.toptracks.TopTracksViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MenuScreen(
    viewModel: MenuViewModel = koinViewModel(),
    topArtistsViewModel: TopArtistsViewModel = koinViewModel(),
    topTracksViewModel: TopTracksViewModel = koinViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    MenuContent(
        state = state,
        onCategorySelected = viewModel::onCategorySelected,
        topArtistsViewModel,
        topTracksViewModel
    )
}

@Composable
fun MenuContent(
    state: MenuScreenState,
    onCategorySelected: (MenuCategory) -> Unit,
    topArtistsViewModel: TopArtistsViewModel,
    topTracksViewModel: TopTracksViewModel,
) {
    val context = LocalContext.current
    Column(verticalArrangement = Arrangement.Top) {
        ProfileTopBar(user = state.userProfile)
        MenuBody(
            categorySelected = state.selectedCategory,
            onCategorySelected = onCategorySelected,
            topArtistsViewModel = topArtistsViewModel,
            topTracksViewModel = topTracksViewModel
        )
    }
}

@Composable
fun MenuBody(
    categorySelected: MenuCategory,
    onCategorySelected: (MenuCategory) -> Unit,
    modifier: Modifier = Modifier,
    topArtistsViewModel: TopArtistsViewModel,
    topTracksViewModel: TopTracksViewModel,
) {

    val selectedIndex = MenuCategory.values().indexOfFirst { it == categorySelected }

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
        MenuCategory.values().forEachIndexed { index, category ->
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
            TopTracksScreen(topTracksViewModel)
        }

        MenuCategory.ARTISTS -> {
            TopArtistsScreen(topArtistsViewModel)
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