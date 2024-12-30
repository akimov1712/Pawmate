package ru.topbun.pawmate.repository

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.topbun.pawmate.database.AppDatabase
import ru.topbun.pawmate.entity.PetType
import ru.topbun.pawmate.entity.Tip

class TipRepository(
    private val context: Context
) {
    private val dao = AppDatabase.getInstance(context).tipDao()

    suspend fun getRandomTip() = dao.getTips().random()
    suspend fun getTipList(type: PetType) = dao.getTips(type)

}