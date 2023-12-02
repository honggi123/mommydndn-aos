package com.mommydndn.app.ui.features.home

import com.mommydndn.app.data.model.babyitem.BabyItem
import com.mommydndn.app.data.model.babyitem.BabyItemMeta
import com.mommydndn.app.domain.model.banner.Banner
import com.mommydndn.app.data.model.care.JobOffer
import com.mommydndn.app.data.model.care.JobSeeker
import com.mommydndn.app.data.model.notification.Notification

data class HomeUiState (
    val isLoading: Boolean = false,
    val errorMessages: String = "",
    val notifications: List<Notification> = emptyList(),
    val jobSeekers: List<JobSeeker> = emptyList(),
    val jobOffers: List<JobOffer> = emptyList(),
    val babyItems: List<BabyItem> = emptyList(),
    val babyItemsPagingMeta: BabyItemMeta = BabyItemMeta(0,1,0),
    val banners: List<Banner> = emptyList(),
)
