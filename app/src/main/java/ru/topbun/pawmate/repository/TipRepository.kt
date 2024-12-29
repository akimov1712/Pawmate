package ru.topbun.pawmate.repository

import android.content.Context
import ru.topbun.pawmate.database.AppDatabase
import ru.topbun.pawmate.entity.PetType

class TipRepository(
    context: Context
) {
    private val dao = AppDatabase.getInstance(context).tipDao()

    suspend fun getRandomTip() = dao.getTips().random()
    suspend fun getTipList(type: PetType) = dao.getTips(type)

}