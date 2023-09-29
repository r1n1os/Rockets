package com.example.rockets.presentation.rockets_list_screen

import com.example.rockets.data.local_database.entities.relations.RocketAndHeight

data class RocketsListState(
    val isLoading: Boolean = false,
    val rocketsList: List<RocketAndHeight> = emptyList(),
    val error: String = ""
)
