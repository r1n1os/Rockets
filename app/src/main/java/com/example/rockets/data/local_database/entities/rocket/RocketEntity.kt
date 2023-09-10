package com.example.rockets.data.local_database.entities.rocket

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.rockets.data.local_database.entities.height.HeightEntity
import com.example.rockets.data.local_database.type_converters.HeightConverter

@Entity(tableName = "Rocket")
data class RocketEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val isActive: Boolean,
    val costPerLaunch: Int,
    val successRatePct: Int,
    val firstFlight: String,
    val country: String,
    val company: String,
    val rocketName: String,
    val rocketType: String,
    @TypeConverters(HeightConverter::class)
    val heightEntity: HeightEntity
)
