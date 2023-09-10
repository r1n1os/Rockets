package com.example.rockets.domain.repository

import com.example.rockets.data.remote.dto.RocketDto

interface RocketsRepository {

    suspend fun getRockets(): List<RocketDto>
}