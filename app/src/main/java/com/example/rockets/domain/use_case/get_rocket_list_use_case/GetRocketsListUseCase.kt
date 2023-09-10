package com.example.rockets.domain.use_case.get_rocket_list_use_case

import com.example.rockets.data.local_database.entities.rocket.RocketEntity
import com.example.rockets.domain.repository.RocketsRepository
import com.example.rockets.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetRocketsListUseCase @Inject constructor(
    private val repository: RocketsRepository
) {
    operator fun invoke(): Flow<Resource<List<RocketEntity>>> = flow {
        try {
            emit(Resource.Loading())
            val rockets = repository.getRockets().map { it.toRocketEntity() }
            emit(Resource.Success(rockets))
        } catch (e: retrofit2.HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Could not reach server. Check your internet connection"))
        }
    }
}