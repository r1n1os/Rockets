package com.example.rockets.data.remote

import com.example.rockets.data.remote.dto.RocketDto
import retrofit2.http.GET

interface RocketsApi {

    @GET("rockets/")
    suspend fun getRocketList(): List<RocketDto>
}