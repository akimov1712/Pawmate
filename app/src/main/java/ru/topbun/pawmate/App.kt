package ru.topbun.pawmate

import android.app.Application
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.await
import ru.topbun.pawmate.entity.Reminder
import ru.topbun.pawmate.workers.ReminderWorker


class App: Application() {

    override fun onCreate() {
        super.onCreate()
        val worker = OneTimeWorkRequestBuilder<ReminderWorker>().build()
        WorkManager.getInstance(this).enqueue(worker)
    }
}