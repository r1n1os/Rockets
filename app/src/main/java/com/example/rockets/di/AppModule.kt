package com.example.rockets.di

import android.content.Context
import androidx.room.Room
import com.example.rockets.data.local_database.RocketsDatabase
import com.example.rockets.data.remote.RocketsApi
import com.example.rockets.data.repository.RocketsRepositoryImpl
import com.example.rockets.domain.repository.RocketsRepository
import com.example.rockets.utils.Urls
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // InstallIn determine for how long this dependencies will leave
object AppModule {

    @Provides
    @Singleton
    fun provideRocketsApi(): RocketsApi {
        return Retrofit.Builder()
            .baseUrl(Urls.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RocketsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRocketsRepository(rocketApi: RocketsApi, rocketsDatabase: RocketsDatabase) : RocketsRepository {
        return RocketsRepositoryImpl(rocketApi, rocketsDatabase)
    }

    @Provides
    @Singleton
    fun provideRocketsDatabase(@ApplicationContext appContext: Context): RocketsDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            RocketsDatabase::class.java,
            "Rockets_database"
        ).build()
    }
}