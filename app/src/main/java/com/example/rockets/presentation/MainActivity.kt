package com.example.rockets.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rockets.presentation.rocket_details_screen.RocketDetailsScreen
import com.example.rockets.presentation.rockets_list_screen.RocketsListScreen
import com.example.rockets.presentation.ui.theme.RocketsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RocketsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = Color.DarkGray
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ScreensRoutes.RocketsListScreen.route
                    ) {
                        composable(
                            route = ScreensRoutes.RocketsListScreen.route
                        ) {
                            RocketsListScreen(navController)
                        }
                        composable(
                            route = ScreensRoutes.RocketDetailsScreen.route + "/{rocket_id_key}"
                        ) {
                            RocketDetailsScreen()
                        }
                    }
                }
            }
        }
    }
}
