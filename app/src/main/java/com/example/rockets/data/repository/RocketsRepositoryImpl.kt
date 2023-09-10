package com.example.rockets.data.repository

import com.example.rockets.data.remote.RocketsApi
import com.example.rockets.data.remote.dto.RocketDto
import com.example.rockets.domain.repository.RocketsRepository
import javax.inject.Inject

class RocketsRepositoryImpl @Inject constructor(
    private val rocketsApi: RocketsApi
): RocketsRepository {
    override suspend fun getRockets(): List<RocketDto> {
        return rocketsApi.getRocketList()
    }
}