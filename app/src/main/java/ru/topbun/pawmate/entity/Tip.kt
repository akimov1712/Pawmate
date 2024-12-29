package ru.topbun.pawmate.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tips")
data class Tip(
    @PrimaryKey
    val id: Int = 0,
    val type: PetType,
    val text: String
)
