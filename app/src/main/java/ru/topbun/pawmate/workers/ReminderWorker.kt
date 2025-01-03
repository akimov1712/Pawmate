package ru.topbun.pawmate.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import ru.topbun.pawmate.entity.Reminder
import ru.topbun.pawmate.utils.NotificationManager


class ReminderWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val reminder = Reminder(
            title = "Привет",
            description = "Пока",
            dateTime = 0,
            frequency = 0
        )
        NotificationManager().showNotification(context, reminder)
        return Result.success()
    }
}
