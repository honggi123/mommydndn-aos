package com.mommydndn.app.ui.home

import com.mommydndn.app.ui.home.components.BannerUiModel
import com.mommydndn.app.ui.home.components.NearbyCareJobUiModel
import com.mommydndn.app.ui.home.components.NearestCareWorkerUiModel

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