package com.example.rockets.presentation

sealed class ScreensRoutes(val route: String) {
    data object RocketsListScree: ScreensRoutes("rockets_list_screen")
}