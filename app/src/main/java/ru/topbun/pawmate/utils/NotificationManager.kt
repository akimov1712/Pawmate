package ru.topbun.pawmate.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.topbun.pawmate.R
import ru.topbun.pawmate.entity.Reminder

class NotificationManager {

    fun showNotification(context: Context, reminder: Reminder) {
        CoroutineScope(Dispatchers.IO).launch {
            val notificationManager = getSystemService(context, NotificationManager::class.java) as NotificationManager
            createNotificationChannel(notificationManager)
            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(reminder.title)
                .setContentText(reminder.description)
                .setSmallIcon(R.drawable.ic_notification)
                .build()
            notificationManager.notify(NOTIFICATION_ID, notification)

        }
    }

    private fun createNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    companion object {
        private const val CHANNEL_ID = "reminder_water_id"
        private const val CHANNEL_NAME = "reminder_water"
        private const val NOTIFICATION_ID = 1
    }

}