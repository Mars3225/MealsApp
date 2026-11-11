package com.example.mealsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): FavoriteDao

    companion object {
        private const val DB_NAME = "mealsapp.db"
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDb(context: Context): AppDatabase {
            INSTANCE?.let { return it }

            return synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).build().also { INSTANCE = it }
            }
        }
    }
}