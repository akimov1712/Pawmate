package ru.topbun.pawmate.repository

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import ru.topbun.pawmate.database.AppDatabase
import ru.topbun.pawmate.database.dao.ReminderDao
import ru.topbun.pawmate.entity.Reminder
import java.util.concurrent.TimeUnit

class ReminderRepository(
    private val context: Context
) {

    private val dao = AppDatabase.getInstance(context).reminderDao()

    fun getReminderList() = dao.getAllReminders()
    suspend fun addReminder(reminder: Reminder) = dao.insertReminder(reminder)
    suspend fun deleteReminder(reminder: Reminder) = dao.deleteReminder(reminder)
    suspend fun updateReminderState(id: Int, isActive: Boolean) = dao.updateReminderStatus(id, isActive)


}