package com.mommydndn.app.ui.features.home

import com.mommydndn.app.data.model.notification.Notification
import com.mommydndn.app.domain.model.banner.Banner
import com.mommydndn.app.domain.model.care.JobOffer
import com.mommydndn.app.domain.model.care.JobSeeker

sealed interface HomeUiState {

    object Loading : HomeUiState
    data class Success(
        val notifications: List<Notification> = emptyList(),
        val jobSeekers: List<JobSeeker> = emptyList(),
        val jobOffers: List<JobOffer> = emptyList(),
        val banners: List<Banner> = emptyList(),
//        val babyItemUiState: HomeBabyItemUiState = HomeBabyItemUiState.Loading()
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