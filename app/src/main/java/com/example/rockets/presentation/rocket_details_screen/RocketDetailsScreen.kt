package com.example.rockets.presentation.rocket_details_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rockets.presentation.rocket_details_screen.view_model.RocketDetailsViewModel

@Composable
fun RocketDetailsScreen(
    viewModel: RocketDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        state.rocket?.let { rocketAndHeight ->
            LazyColumn(
                contentPadding = PaddingValues(22.dp)
            ) {
                item {
                    Text(
                        text = rocketAndHeight.rocketEntity.rocketName,
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row {
                        Text(
                            text = "Status: ",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Box(
                            modifier = Modifier
                                .width(12.dp)
                                .height(12.dp)
                                .padding(top = 5.dp)
                                .aspectRatio(1f)
                                .background(
                                    if (rocketAndHeight.rocketEntity.isActive) Color.Green else Color.Red,
                                    shape = CircleShape
                                )
                        ) {

                        }
                    }

                    Spacer(modifier = Modifier.height(21.dp))/* AsyncImage(
                         model = rocketAndHeight.rocketEntity.
                     )*/
                    Text(
                        text = rocketAndHeight.rocketEntity.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}