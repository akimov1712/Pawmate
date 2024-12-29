package ru.topbun.pawmate.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.topbun.pawmate.entity.Pet

@Dao
interface PetDao {

    @Insert
    suspend fun insertPet(pet: Pet)

    @Query("SELECT * FROM pets WHERE id = :id LIMIT 1")
    suspend fun getPet(id: Int): Pet

    @Query("SELECT * FROM pets")
    fun getPetList(): Flow<List<Pet>>

}