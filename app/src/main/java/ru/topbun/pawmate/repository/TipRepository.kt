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

    init {
        initTips()
    }

    suspend fun getRandomTip() = dao.getTips().random()
    suspend fun getTipList(type: PetType) = dao.getTips(type)

    private fun initTips() = CoroutineScope(Dispatchers.IO).launch {
        if (dao.getTips().size < 50){
            dao.deleteAll()
            val jsonString = context.assets.open("tips.json").bufferedReader().use { it.readText() }
            val tipsList = Json.decodeFromString<List<Tip>>(jsonString)
            dao.insertTips(tipsList)
        }
    }


}