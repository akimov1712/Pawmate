package ru.topbun.pawmate.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.topbun.pawmate.database.dao.PetDao
import ru.topbun.pawmate.database.dao.ReminderDao
import ru.topbun.pawmate.database.dao.TipDao
import ru.topbun.pawmate.database.dao.UserDao
import ru.topbun.pawmate.entity.Pet
import ru.topbun.pawmate.entity.Reminder
import ru.topbun.pawmate.entity.Tip
import ru.topbun.pawmate.entity.User

@Database(
    entities = [
        User::class,
        Tip::class,
        Pet::class,
        Reminder::class,
    ],
    exportSchema = true,
    version = 3,
    autoMigrations = [
        AutoMigration(2,3)
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun tipDao(): TipDao
    abstract fun petDao(): PetDao
    abstract fun reminderDao(): ReminderDao

    companion object {

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""
            CREATE TABLE IF NOT EXISTS `reminders` (
                `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                `dateTime` INTEGER NOT NULL,
                `description` TEXT NOT NULL,
                `frequency` TEXT NOT NULL,
                `isActive` INTEGER NOT NULL,
                `title` TEXT NOT NULL
            )
        """.trimIndent())
            }
        }

        private const val DB_NAME = "pawmate.db"
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context) = INSTANCE ?: synchronized(Unit) {
            INSTANCE ?: createDatabase(context).also { INSTANCE = it }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context, AppDatabase::class.java, DB_NAME
        ).createFromAsset("tips.db").addMigrations(MIGRATION_1_2).build()

    }

}