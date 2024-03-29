package com.wachon.spotiwrap.features.collage.presentation.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.PixelCopy
import android.view.View
import androidx.annotation.RequiresApi
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInRoot
import androidx.core.content.FileProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


object BitmapUtil {

    private val job: Job = SupervisorJob()
    private val coroutinesScope = CoroutineScope(Dispatchers.Default + job)

    fun createBitmapFromCompose(
        context: Context,
        view: View,
        layoutCoordinates: LayoutCoordinates,
        onBitmapCreated: (Bitmap) -> Unit
    ) {
        coroutinesScope.launch {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createBitmapWithPixelCopy(
                    context = context,
                    view = view,
                    layoutCoordinates = layoutCoordinates,
                    onBitmapCreated = onBitmapCreated
                )
            } else {
                createBitmap(
                    view = view,
                    layoutCoordinates = layoutCoordinates,
                    onBitmapCreated = onBitmapCreated
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createBitmapWithPixelCopy(
        context: Context,
        view: View,
        layoutCoordinates: LayoutCoordinates,
        onBitmapCreated: (Bitmap) -> Unit
    ) {
        try {
            if (layoutCoordinates.isAttached
                && layoutCoordinates.boundsInRoot().size.width.toInt() > 0
                && layoutCoordinates.boundsInRoot().size.height.toInt() > 0
            ) {
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
                        onBitmapCreated(bitmap)
                    },
                    Handler(Looper.getMainLooper())
                )
            } else {
                onBitmapCreated(
                    Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun createBitmap(
        view: View,
        layoutCoordinates: LayoutCoordinates,
        onBitmapCreated: (Bitmap) -> Unit
    ) {
        if (layoutCoordinates.isAttached) {
            val bitmap = Bitmap.createBitmap(
                layoutCoordinates.boundsInRoot().size.width.toInt(),
                layoutCoordinates.boundsInRoot().size.height.toInt(),
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            view.draw(canvas)
            canvas.setBitmap(null)
            onBitmapCreated(bitmap)
        } else {
            onBitmapCreated(
                Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
            )
        }
    }


    fun shareImage(context: Context, bitmap: Bitmap) {
        try {
            val cachePath = File(context.cacheDir, "images")
            cachePath.mkdirs()
            val stream = FileOutputStream("$cachePath/image.png")
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val imagePath = File(context.cacheDir, "images")
        val newFile = File(imagePath, "image.png")
        val contentUri =
            FileProvider.getUriForFile(context, "com.wachon.spotiwrap.provider", newFile)

        if (contentUri != null) {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            shareIntent.setDataAndType(contentUri, context.contentResolver.getType(contentUri))
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri)
            context.startActivity(Intent.createChooser(shareIntent, "Choose an app"))
        }
    }
}