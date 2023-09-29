package com.example.rockets.presentation.rockets_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rockets.presentation.ScreensRoutes
import com.example.rockets.presentation.rockets_list_screen.components.RocketListItem
import com.example.rockets.presentation.rockets_list_screen.view_model.RocketsViewModel

@Composable
fun RocketsListScreen(
    navController: NavController,
    viewModel: RocketsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.rocketsList) { rocketAndHeight ->
                RocketListItem(rocketAndHeight = rocketAndHeight, onRocketClick = {
                    navController.navigate(ScreensRoutes.RocketDetailsScreen.route + "/${rocketAndHeight.rocketEntity.id.toString()}")
                })
            }
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}