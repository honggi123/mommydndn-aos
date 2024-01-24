package com.mommydndn.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.ui.components.banner.BannerUiModel
import com.mommydndn.app.ui.home.components.NearbyCareJobUiModel
import com.mommydndn.app.ui.home.components.NearestCareWorkerUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
        }
    }
}

sealed interface HomeUiState {

    data object Loading : HomeUiState

    data class Success(
        val hasUnreadNotification: Boolean = false,
        val banners: List<BannerUiModel> = emptyList(),
        val nearestCareProviders: List<NearestCareWorkerUiModel> = emptyList(),
        val nearbyJobOpenings: List<NearbyCareJobUiModel> = emptyList(),
    ) : HomeUiState

    data class Failure(val exception: Exception) : HomeUiState
}