package com.example.rockets.data.local_database.entities.rocket

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rockets.data.local_database.entities.height.HeightEntity

interface RocketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRockets(rocketsList: List<RocketEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeight(heightEntity: HeightEntity)

    @Query("SELECT * From rocket")
    suspend fun getListWithRockets() : List<RocketEntity>
}