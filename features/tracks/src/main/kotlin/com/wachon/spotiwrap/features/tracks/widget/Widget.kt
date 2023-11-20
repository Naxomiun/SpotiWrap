package com.wachon.spotiwrap.features.tracks.widget

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.ImageProvider
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.ContentScale
import androidx.glance.layout.fillMaxSize
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.wachon.spotiwrap.core.design.theme.BubblegumPink
import com.wachon.spotiwrap.features.tracks.domain.GetUserTopTracksUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TopTrackWidget : GlanceAppWidget(), KoinComponent {

    private val getUserTopTracksUseCase: GetUserTopTracksUseCase by inject()

    override val stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {

            GlanceTheme {
                val topTracks by getUserTopTracksUseCase().collectAsState(initial = null)

                topTracks?.let {
                    Box(
                        modifier = GlanceModifier
                            .fillMaxSize()
                            .appWidgetBackground()
                            .background(ColorProvider(androidx.compose.ui.graphics.Color.Black),)

                    ) {
                        Text(
                            text = it[0].title,
                            style = TextStyle(
                                color = ColorProvider(BubblegumPink),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            ),
                        )
                    }
                }
            }
        }
    }




}

class TopTrackWidgetReceiver: GlanceAppWidgetReceiver()  {

    override val glanceAppWidget: GlanceAppWidget = TopTrackWidget()

}