package com.example.rockets.data.local_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rockets.data.local_database.entities.rocket.RocketDao
import com.example.rockets.data.local_database.entities.rocket.RocketEntity
import com.example.rockets.data.local_database.type_converters.HeightConverter


@Database(
    entities = [RocketEntity::class],
    version = 1
)
@TypeConverters(HeightConverter::class,)
abstract class RocketsDatabase: RoomDatabase() {
    abstract val dao: RocketDao

    companion object {
        @Volatile
        private var instance: RocketsDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "DB_NAME"

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            RocketsDatabase::class.java,
            DB_NAME
        ).build()

        fun destroyInstance() {
            instance = null
        }

        fun deleteAll() {
            instance?.clearAllTables()
        }
    }
}