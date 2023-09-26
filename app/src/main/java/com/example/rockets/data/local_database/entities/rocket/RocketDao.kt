package com.example.rockets.data.local_database.entities.rocket

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.rockets.data.local_database.entities.height.HeightEntity
import com.example.rockets.data.local_database.entities.payload_weights.PayloadWeightsEntity
import com.example.rockets.data.local_database.entities.relations.RocketAndHeight
import com.example.rockets.data.local_database.entities.relations.RocketsAndHeightWithPayloadWeightEntity

@Dao
interface RocketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRockets(rocketsList: List<RocketEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeight(heightEntity: HeightEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPayloadWeight(payloadWeightEntity: PayloadWeightsEntity)

    @Transaction
    @Query("SELECT * From rocket")
    suspend fun getListWithRockets() : List<RocketAndHeight>

    @Transaction
    @Query("SELECT * From rocket WHERE id=:rocketId")
    suspend fun getRocketById(rocketId: String) : RocketAndHeight

    @Transaction
    @Query("SELECT * FROM rocket")
    fun getRocketAndWeightWithPayloadWeight(): RocketsAndHeightWithPayloadWeightEntity
}