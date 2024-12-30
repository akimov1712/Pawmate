package ru.topbun.pawmate.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.topbun.pawmate.database.dao.PetDao
import ru.topbun.pawmate.database.dao.TipDao
import ru.topbun.pawmate.database.dao.UserDao
import ru.topbun.pawmate.entity.Pet
import ru.topbun.pawmate.entity.Tip
import ru.topbun.pawmate.entity.User

@Database(
    entities = [
        User::class,
        Tip::class,
        Pet::class,
    ],
    exportSchema = false,
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun tipDao(): TipDao
    abstract fun petDao(): PetDao

    companion object {

        private const val DB_NAME = "pawmate.db"
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context) = INSTANCE ?: synchronized(Unit) {
            INSTANCE ?: createDatabase(context).also { INSTANCE = it }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context, AppDatabase::class.java, DB_NAME
        ).createFromAsset("tips.db").build()

    }

}