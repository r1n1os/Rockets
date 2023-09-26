package com.example.rockets.domain.use_case.query_rocket_details_from_local_database_by_id

import com.example.rockets.data.local_database.entities.relations.RocketAndHeight
import com.example.rockets.domain.repository.RocketsRepository
import com.example.rockets.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class QueryRocketDetailsFromLocalDatabaseById @Inject constructor(
    private val repository: RocketsRepository
) {
    operator fun invoke(rocketId: String): Flow<Resource<RocketAndHeight>> = flow {
        try {
            val rocketsList = repository.queryRocketDetailsFromLocalDatabaseById(rocketId)
            emit(Resource.Success(rocketsList))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong with retrieving data from local database"))
        }
    }
}