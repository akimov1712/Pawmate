package ru.topbun.pawmate.repository

import android.content.Context
import ru.topbun.pawmate.cache.AppSettings
import ru.topbun.pawmate.cache.AppSettings.dataStore
import ru.topbun.pawmate.cache.Settings
import ru.topbun.pawmate.cache.getUserId
import ru.topbun.pawmate.cache.saveUserId
import ru.topbun.pawmate.database.AppDatabase
import ru.topbun.pawmate.entity.User

class UserRepository(private val context: Context) {

    private val settings = context.dataStore
    private val dao = AppDatabase.getInstance(context).userDao()

    suspend fun isUserAuth() = settings.getUserId() != null

    suspend fun singUp(user: User): Boolean{
        val user = dao.addUser(user)
        settings.saveUserId(user.id)
        return settings.getUserId() == user.id
    }

    suspend fun login(email: String, password: String): Boolean{
        val user = dao.getUser(email, password) ?: return false
        settings.saveUserId(user.id)
        return settings.getUserId() == user.id
    }

}