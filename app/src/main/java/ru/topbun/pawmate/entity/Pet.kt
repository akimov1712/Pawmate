package ru.topbun.pawmate.entity

import androidx.room.Entity

@Entity(tableName = "pets")
data class Pet(
    val id: Int = 0,
    val name: String,
    val age: Int,
    val breed: String?,
    val image: String?,
    val type: PetType
)
