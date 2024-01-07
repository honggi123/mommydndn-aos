package com.mommydndn.app.feature.home

import com.mommydndn.app.feature.home.components.Banner
import com.mommydndn.app.feature.home.components.NearbyJobOpening
import com.mommydndn.app.feature.home.components.NearestCareProvider

sealed interface HomeUiState {

    data object Loading : HomeUiState

    data class Success(
        val hasUnreadNotification: Boolean = false,
        val banners: List<Banner> = emptyList(),
        val nearestCareProviders: List<NearestCareProvider> = emptyList(),
        val nearbyJobOpenings: List<NearbyJobOpening> = emptyList(),
    ) : HomeUiState

    data class Failure(val exception: Exception) : HomeUiState
}