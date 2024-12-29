package ru.topbun.pawmate.repository

import android.content.Context
import ru.topbun.pawmate.database.AppDatabase

class PetRepository(
    context: Context
) {
    private val dao = AppDatabase.getInstance(context).petDao()

    

}