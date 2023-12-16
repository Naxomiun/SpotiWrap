package com.wachon.spotiwrap.features.collage.presentation

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.lerp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.wachon.spotiwrap.core.common.R
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.core.design.R.drawable.logo_spotify_green
import com.wachon.spotiwrap.core.design.components.ButtonIcon
import com.wachon.spotiwrap.core.design.components.LoadingView
import com.wachon.spotiwrap.core.design.components.ScreenTitleWithBack
import com.wachon.spotiwrap.core.design.components.TextWithLine
import com.wachon.spotiwrap.core.design.extensions.indicatorOffsetForPage
import com.wachon.spotiwrap.core.design.extensions.startOffsetForPage
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.BubblegumPink
import com.wachon.spotiwrap.core.design.theme.SpotifyBlack
import com.wachon.spotiwrap.core.design.theme.SpotifyGreen
import com.wachon.spotiwrap.core.design.theme.Title
import com.wachon.spotiwrap.features.artists.domain.TopGenreUI
import com.wachon.spotiwrap.features.collage.presentation.Pages.LAST_6_MONTHS
import com.wachon.spotiwrap.features.collage.presentation.Pages.LAST_MONTH
import com.wachon.spotiwrap.features.collage.presentation.Pages.LIFETIME
import com.wachon.spotiwrap.features.collage.presentation.utils.BitmapUtil
import com.wachon.spotiwrap.features.collage.presentation.utils.BitmapUtil.shareImage
import kotlinx.coroutines.coroutineScope
import org.koin.androidx.compose.koinViewModel

@Composable
fun PreviewScreen(
    viewModel: PreviewViewModel = koinViewModel(),
    navigateUp: () -> Unit
) {
    val context = LocalContext.current
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    if (state.isLoading) {
        LoadingView()
    } else {
        PreviewContent(
            context = context,
            viewModel = viewModel,
            state = state,
            navigateUp = navigateUp
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PreviewContent(
    context: Context,
    viewModel: PreviewViewModel,
    state: PreviewScreenState,
    navigateUp: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { Pages.entries.size })

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .systemBarsPadding(),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.weight(0.1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ScreenTitleWithBack(
                text = LocalContext.current.getString(R.string.preview_title),
                navigateUp = navigateUp
            )
        }
        PagerContent(state = state, pagerState = pagerState) {
            viewModel.storePreviewBitmap(it)
        }
        PagerIndicator(pagerState = pagerState)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ButtonIcon(
                text = context.getString(R.string.preview_share),
                icon = Icons.Filled.Share
            ) {
                shareImage(context = context, bitmap = viewModel.getPreviewBitmap())
            }
        }
    }
}

context(ColumnScope)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerContent(
    state: PreviewScreenState,
    pagerState: PagerState,
    onBitmapCreated: (Bitmap) -> Unit
) {
    val view = LocalView.current
    val context = LocalContext.current

    Row(modifier = Modifier.weight(0.7f)) {
        HorizontalPager(
            modifier = Modifier
                .onGloballyPositioned {
                    BitmapUtil.createBitmapFromCompose(
                        context = context,
                        view = view,
                        layoutCoordinates = it,
                        onBitmapCreated = onBitmapCreated
                    )
                },
            contentPadding = PaddingValues(32.dp),
            pageSpacing = 1.dp,
            beyondBoundsPageCount = 1,
            state = pagerState
        ) { pageIndex ->
            when (Pages.entries[pageIndex]) {
                LAST_MONTH -> PageContent(
                    index = pageIndex,
                    pagerState = pagerState,
                    title = LocalContext.current.getString(R.string.last_month),
                    image = state.artistsShort.firstOrNull()?.imageUrl ?: "",
                    artists = state.artistsShort,
                    tracks = state.tracksShort,
                    genres = state.genresShort,
                )

                LAST_6_MONTHS -> PageContent(
                    index = pageIndex,
                    pagerState = pagerState,
                    title = LocalContext.current.getString(R.string.last_6_months),
                    image = state.artistsMedium.firstOrNull()?.imageUrl ?: "",
                    artists = state.artistsMedium,
                    tracks = state.tracksMedium,
                    genres = state.genresMedium,
                )

                LIFETIME -> PageContent(
                    index = pageIndex,
                    pagerState = pagerState,
                    title = LocalContext.current.getString(R.string.lifetime),
                    image = state.artistsLong.firstOrNull()?.imageUrl ?: "",
                    artists = state.artistsLong,
                    tracks = state.tracksLong,
                    genres = state.genresLong,
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageContent(
    index: Int,
    pagerState: PagerState,
    title: String,
    image: String,
    artists: List<ArtistModel>,
    tracks: List<TrackModel>,
    genres: List<TopGenreUI>,
) {
    Column(
        modifier = Modifier
            .zIndex(index * 10f)
            .padding(PaddingValues(8.dp))
            .graphicsLayer {
                val startOffset = pagerState.startOffsetForPage(index)
                translationX = size.width * (startOffset * .99f)
                alpha = (2f - startOffset) / 2f
                val scale = 1f - (startOffset * .1f)
                scaleX = scale
                scaleY = scale
            }
            .clip(RoundedCornerShape(5))
            .background(color = SpotifyBlack),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier
                .weight(0.1f)
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextWithLine(
                text = title,
                textStyle = Body.copy(fontSize = 24.sp, fontWeight = FontWeight.W700)
            )
        }

        Row(
            modifier = Modifier
                .weight(0.4f)
                .padding(32.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = image,
                contentDescription = image,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .clip(RectangleShape)
                    .size(32.dp)
            )
        }

        Row(
            modifier = Modifier
                .weight(0.2f)
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top,
        ) {
            Column(
                modifier = Modifier
                    .weight(0.45f)
            ) {
                Text(
                    text = LocalContext.current.getString(R.string.home_top_artist_title),
                    style = Body.copy(fontSize = 10.sp),
                    color = BubblegumPink
                )
                artists.take(5).forEachIndexed { index, artist ->
                    Text(
                        text = "#${index + 1} ${artist.name}",
                        style = Title.copy(fontSize = 13.sp),
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

            }

            Spacer(modifier = Modifier.weight(0.1f))

            Column(
                modifier = Modifier
                    .weight(0.45f)
            ) {
                Text(
                    text = LocalContext.current.getString(R.string.home_top_artist_title),
                    style = Body.copy(fontSize = 10.sp),
                    color = BubblegumPink
                )
                tracks.take(5).forEachIndexed { index, track ->
                    Text(
                        text = "#${index + 1} ${track.title}",
                        style = Title.copy(fontSize = 13.sp),
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .weight(0.05f)
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = LocalContext.current.getString(R.string.home_top_genres_title),
                    style = Body.copy(fontSize = 10.sp),
                    color = BubblegumPink
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    genres.take(3).forEach { genre ->
                        Text(
                            modifier = Modifier.weight(0.2f),
                            text = genre.genreName,
                            textAlign = TextAlign.Center,
                            style = Title.copy(fontSize = 13.sp),
                            color = Color.White,
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .weight(0.1f)
                .padding(horizontal = 24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier.size(75.dp),
                painter = painterResource(logo_spotify_green),
                contentDescription = "",
                colorFilter = ColorFilter.tint(SpotifyGreen)
            )
        }
    }
}

context(ColumnScope)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .weight(0.1f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {

        val selectedTextStyle = remember {
            Title.copy(fontSize = 36.sp, color = BubblegumPink)
        }

        val defaultTextStyle = remember {
            Title.copy(fontSize = 18.sp, color = BubblegumPink)
        }

        for (i in 0 until Pages.entries.size) {
            Box(
                modifier = Modifier
                    .size(height = 48.dp, width = 8.dp)
                    .align(Alignment.CenterVertically),
                contentAlignment = Alignment.CenterEnd
            ) {
                val offset = pagerState.indicatorOffsetForPage(i)
                Text(
                    text = "|",
                    modifier = Modifier.fillMaxWidth(),
                    style = lerp(defaultTextStyle, selectedTextStyle, offset),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

enum class Pages {
    LAST_MONTH, LAST_6_MONTHS, LIFETIME
}

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun PreviewPageContent() {
    PageContent(
        index = 1,
        title = "Last month",
        image = "",
        artists = listOf(),
        tracks = listOf(),
        genres = listOf(
            TopGenreUI("Aaaaaaa", 0.5f),
            TopGenreUI("Aaaaaaa", 0.5f),
            TopGenreUI("Aaaaaaa", 0.5f)
        ),
        pagerState = rememberPagerState(pageCount = { 1 })
    )
}
