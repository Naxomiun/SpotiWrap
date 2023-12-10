package com.wachon.spotiwrap.data.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.ForegroundInfo
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.wachon.spotiwrap.core.common.R.drawable.fame_new
import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.data.R
import com.wachon.spotiwrap.data.repository.ArtistsRepository
import com.wachon.spotiwrap.data.repository.TracksRepository
import com.wachon.spotiwrap.data.repository.UserRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SyncWorker(
    appContext: Context,
    parameters: WorkerParameters
) : CoroutineWorker(appContext, parameters), KoinComponent {

    private val dispatcherProvider: DispatcherProvider by inject()
    private val tracksRepository: TracksRepository by inject()
    private val userRepository: UserRepository by inject()
    private val artistsRepository: ArtistsRepository by inject()

    override suspend fun doWork(): Result = withContext(dispatcherProvider.background) {

        val syncedSuccessfully = awaitAll(
            async { tracksRepository.sync() },
            async { userRepository.sync() },
            async { artistsRepository.sync() }
        ).all { it.isSuccess }

        if (syncedSuccessfully) {
            Result.success()
        } else {
            Result.retry()
        }

    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "NOTIFICATION_CHANNEL_ID",
                "NOTIFICATION_CHANNEL_NAME",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(applicationContext, "NOTIFICATION_CHANNEL_ID")
            .setSmallIcon(fame_new)
            .setOngoing(true)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .setLocalOnly(true)
            .setVisibility(NotificationCompat.VISIBILITY_SECRET)
            .setContent(
                RemoteViews(
                    applicationContext.packageName,
                    R.layout.sync_notification_layout
                )
            )
            .build()
        return ForegroundInfo(1337, notification)
    }

    companion object {
        fun startUpSyncWork() = OneTimeWorkRequestBuilder<SyncWorker>()
            .setConstraints(SyncConstraints)
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .build()
    }

}

object Sync {
    fun initialize(context: Context) {
        WorkManager
            .getInstance(context)
            .enqueueUniqueWork(
                SyncWorkName,
                ExistingWorkPolicy.KEEP,
                SyncWorker.startUpSyncWork(),
            )
    }
}

internal const val SyncWorkName = "SyncWorkName"

val SyncConstraints
    get() = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

