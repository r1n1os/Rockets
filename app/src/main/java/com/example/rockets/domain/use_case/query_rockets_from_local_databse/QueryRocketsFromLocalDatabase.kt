package com.example.rockets.domain.use_case.query_rockets_from_local_databse

import android.util.Log
import com.example.rockets.data.local_database.entities.relations.RocketAndHeight

import com.example.rockets.domain.repository.RocketsRepository
import com.example.rockets.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class QueryRocketsFromLocalDatabase @Inject constructor(
    private val repository: RocketsRepository
){
    operator fun invoke(): Flow<Resource<List<RocketAndHeight>>> = flow {
        try {
           val rocketsList = repository.queryRocketListFromLocalDatabase()
            Log.d("dfsdfsdf in", (rocketsList.size ?: 0).toString())
            emit(Resource.Success(rocketsList))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong with retrieving data from local database"))
        }
    }
}