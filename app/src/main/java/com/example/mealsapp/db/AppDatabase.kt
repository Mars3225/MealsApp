package com.example.mealsapp.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@androidx.room.Database(entities = [FavoriteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): FavoriteDao

    companion object {
        fun getDb(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "db"
            ).build()
        }
    }
}
