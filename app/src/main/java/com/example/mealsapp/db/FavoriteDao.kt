package com.example.mealsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(meal: FavoriteEntity)

    @Delete
    suspend fun delete(meal: FavoriteEntity)

    @Query("SELECT * FROM favorites")
    fun getAll(): LiveData<List<FavoriteEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id=:id)")
    suspend fun isFavorite(id: Int): Boolean
}