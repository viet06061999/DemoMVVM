package com.sun.mvvm.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sun.mvvm.data.model.User
import com.sun.mvvm.data.source.local.database.dao.UserDao

@Database(
    entities = [User::class],
    version = DatabaseConstants.DATABASE_VERSION,
    exportSchema = false
)

abstract class AppDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao
}
