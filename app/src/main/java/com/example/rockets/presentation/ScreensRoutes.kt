package com.example.rockets.presentation

sealed class ScreensRoutes(val route: String) {
     object RocketsListScree: ScreensRoutes("rockets_list_screen")
}