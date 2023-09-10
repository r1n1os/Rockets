package com.example.rockets.presentation.rockets_list_screen

import com.example.rockets.data.local_database.entities.rocket.RocketEntity

data class RocketsListState(
    val isLoading: Boolean = false,
    val rocketsList: List<RocketEntity> = emptyList(),
    val error: String = ""
)
