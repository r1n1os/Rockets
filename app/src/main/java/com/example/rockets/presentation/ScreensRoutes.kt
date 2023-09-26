package com.example.rockets.presentation

sealed class ScreensRoutes(val route: String) {
     object RocketsListScreen: ScreensRoutes("rockets_list_screen")
}