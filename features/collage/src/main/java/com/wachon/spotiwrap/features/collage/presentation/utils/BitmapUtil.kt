package com.wachon.spotiwrap.features.collage.presentation.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Build
import android.view.PixelCopy
import android.view.View
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInRoot

object BitmapUtil {
    fun createBitmapFromCompose(
        context: Context,
        view: View,
        layoutCoordinates: LayoutCoordinates,
        onBitmapCreated: (Bitmap) -> Unit
    ) {
        val handler = view.handler
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                handler.postDelayed({
                    val bitmap = Bitmap.createBitmap(
                        layoutCoordinates.boundsInRoot().size.width.toInt(),
                        layoutCoordinates.boundsInRoot().size.height.toInt(),
                        Bitmap.Config.ARGB_8888
                    )
                    val location = IntArray(2)
                    view.getLocationInWindow(location)
                    PixelCopy.request(
                        (context as Activity).window,
                        Rect(
                            layoutCoordinates.boundsInRoot().left.toInt(),
                            layoutCoordinates.boundsInRoot().top.toInt(),
                            layoutCoordinates.boundsInRoot().right.toInt(),
                            layoutCoordinates.boundsInRoot().bottom.toInt()
                        ),
                        bitmap,
                        {
                            onBitmapCreated.invoke(bitmap)
                        },
                        handler
                    )
                }, 1000)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            val bitmap = Bitmap.createBitmap(
                layoutCoordinates.boundsInRoot().size.width.toInt(),
                layoutCoordinates.boundsInRoot().size.height.toInt(),
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            view.draw(canvas)
            canvas.setBitmap(null)
            onBitmapCreated.invoke(bitmap)
        }
    }
}