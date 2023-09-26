package com.example.rockets.presentation.rocket_details_screen

import com.example.rockets.data.local_database.entities.relations.RocketAndHeight
import com.example.rockets.data.local_database.entities.rocket.RocketEntity

data class RocketDetailsState(
    val isLoading: Boolean = false,
    val rocket: RocketAndHeight?,
    val error: String = ""
)
