package com.example.rockets.domain.use_case.save_rockets_into_local_database_use_case

import android.content.res.Resources
import android.util.Log
import com.example.rockets.data.local_database.entities.rocket.RocketEntity
import com.example.rockets.data.remote.dto.RocketDto
import com.example.rockets.domain.repository.RocketsRepository
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.rockets.utils.Resource
import kotlinx.coroutines.flow.flow

class SaveRocketsIntoLocalDatabaseUseCase @Inject constructor(
    private val repository: RocketsRepository
){
    operator fun invoke(rocketDtoList: List<RocketDto>): Flow<Resource<String>> = flow {
        try {
            repository.saveRocketsIntoLocalDatabase(rocketDtoList)
            emit(Resource.Success("Succeed"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong with saving data into local database"))
        }
    }
}