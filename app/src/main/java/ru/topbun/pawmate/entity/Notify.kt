package ru.topbun.pawmate.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications")
data class Notify(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val type: NotifyType,
    val alarmDate: Long,
    val periodTime: Long,
    val isEnabled: Boolean
)
