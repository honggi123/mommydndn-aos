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
        // baby_items?
    ) : HomeUiState

    data class Failure(val exception: Exception) : HomeUiState
}

//sealed interface HomeBabyItemUiState {
//
//    val babyItems: List<com.mommydndn.app.domain.model.care.BabyItem>
//
//    data class Loading(
//        override val babyItems: List<com.mommydndn.app.domain.model.care.BabyItem> = emptyList(),
//    ) : HomeBabyItemUiState
//
//    data class Success(
//        override val babyItems: List<com.mommydndn.app.domain.model.care.BabyItem> = emptyList(),
//        val babyItemsPagingMeta: com.mommydndn.app.domain.model.care.BabyItemMeta = com.mommydndn.app.domain.model.care.BabyItemMeta(0, 1, 0)
//    ) : HomeBabyItemUiState
//}