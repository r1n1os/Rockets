package com.example.rockets.presentation.rocket_details_screen.view_model

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rockets.domain.use_case.query_rocket_details_from_local_database_by_id.QueryRocketDetailsFromLocalDatabaseById
import com.example.rockets.domain.use_case.query_rockets_from_local_databse.QueryRocketsFromLocalDatabase
import com.example.rockets.presentation.rocket_details_screen.RocketDetailsState
import com.example.rockets.presentation.rockets_list_screen.RocketsListState
import com.example.rockets.utils.Resource
import com.example.rockets.utils.Urls.ROCKET_ID_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RocketDetailsViewModel @Inject constructor(
    private val queryRocketDetailsFromLocalDatabaseById: QueryRocketDetailsFromLocalDatabaseById,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _rocketDetailsState = mutableStateOf(RocketDetailsState(rocket = null))
    val state: State<RocketDetailsState> = _rocketDetailsState

    init {
        Log.d("dfsafadsf", "rocketId: ")

        savedStateHandle.get<String>(ROCKET_ID_KEY)?.let { rocketId ->
            queryRocketsOffline(rocketId)
        }
    }

    private fun queryRocketsOffline(rocketId: String) {
        Log.d("dfsafadsf", "rocketId: $rocketId")
        queryRocketDetailsFromLocalDatabaseById(rocketId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _rocketDetailsState.value = RocketDetailsState(rocket = result.data)
                }

                is Resource.Error -> {
                    _rocketDetailsState.value = RocketDetailsState(
                        error = result.message ?: "Unknown error occurred", rocket = null
                    )
                }

                is Resource.Loading -> {
                    _rocketDetailsState.value = RocketDetailsState(isLoading = true, rocket = null)
                }
            }
        }.launchIn(viewModelScope)
    }
}