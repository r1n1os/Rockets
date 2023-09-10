package com.example.rockets.presentation.rockets_list_screen.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.rockets.domain.use_case.get_rocket_list_use_case.GetRocketsListUseCase
import com.example.rockets.presentation.rockets_list_screen.RocketsListState
import com.example.rockets.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RocketsViewModel @Inject constructor(
    private val getRocketsListUseCase: GetRocketsListUseCase
) : ViewModel() {

    private val _rocketListState = mutableStateOf(RocketsListState())
    val state: State<RocketsListState> = _rocketListState

    init {
        getRocketsList()
    }

    private fun getRocketsList() {
        getRocketsListUseCase().onEach {  result ->
            when(result) {
                is Resource.Success -> {
                    _rocketListState.value= RocketsListState(rocketsList = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _rocketListState.value= RocketsListState(error = result.message ?:
                    "Unknown error occurred")
                }
                is Resource.Loading -> {
                    _rocketListState.value= RocketsListState(isLoading = true)
                }
            }
        }
    }
}