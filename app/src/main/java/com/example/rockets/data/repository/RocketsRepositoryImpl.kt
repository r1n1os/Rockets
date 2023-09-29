package com.example.rockets.data.repository

import com.example.rockets.data.local_database.RocketsDatabase
import com.example.rockets.data.local_database.entities.relations.RocketAndHeight
import com.example.rockets.data.local_database.entities.relations.RocketWithPayloadWeights
import com.example.rockets.data.remote.RocketsApi
import com.example.rockets.data.remote.dto.RocketDto
import com.example.rockets.domain.repository.RocketsRepository
import javax.inject.Inject

class RocketsRepositoryImpl @Inject constructor(
    private val rocketsApi: RocketsApi,
    private val rocketsDatabase: RocketsDatabase
) : RocketsRepository {
    override suspend fun getRockets(): List<RocketDto> {
        return rocketsApi.getRocketList()
    }

    override suspend fun saveRocketsIntoLocalDatabase(rocketsDtoList: List<RocketDto>) {
        rocketsDatabase.dao.insertRockets(rocketsDtoList.map { it.toRocketEntity() })
        rocketsDtoList.map { it }.forEach { rocketDto ->
            rocketsDatabase.dao.insertHeight(rocketDto.height.toRocketEntity(rocketDto.id))
            rocketDto.payloadWeights.forEach { payloadWeight ->
                rocketsDatabase.dao.insertPayloadWeight(
                    payloadWeight.toPayloadWeightEntity(
                        rocketDto.id
                    )
                )
            }
        }
    }

    override suspend fun queryRocketListFromLocalDatabase(): List<RocketAndHeight> {
        return rocketsDatabase.dao.getListWithRockets()
    }

    override suspend fun queryRocketDetailsFromLocalDatabaseById(rocketId: String): RocketAndHeight {
        return rocketsDatabase.dao.getRocketById(rocketId)
    }

    override suspend fun queryRocketDetailsWithPayloadWeightFromLocalDatabaseById(rocketId: String): RocketWithPayloadWeights {
        return rocketsDatabase.dao.getRocketWithPayloadWeight(rocketId)
    }
}