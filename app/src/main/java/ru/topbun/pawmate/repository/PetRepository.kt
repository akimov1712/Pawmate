package ru.topbun.pawmate.repository

import android.content.Context
import kotlinx.coroutines.flow.Flow
import ru.topbun.pawmate.database.AppDatabase
import ru.topbun.pawmate.entity.Pet

class PetRepository(
    context: Context
) {
    private val dao = AppDatabase.getInstance(context).petDao()

    fun getPetList() = dao.getPetList()
    fun getPet(id: Int) = dao.getPet(id)
    suspend fun addPep(pet: Pet) = dao.insertPet(pet)


}