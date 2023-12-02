package com.mommydndn.app.ui.features.home

import com.mommydndn.app.data.model.banner.Banner
import com.mommydndn.app.data.model.care.JobOffer
import com.mommydndn.app.data.model.care.JobSeeker
import com.mommydndn.app.data.model.notification.Notification

data class HomeUiState (
    val isLoading: Boolean = false,
    val errorMessages: String = "",
    val notifications: List<Notification> = emptyList(),
    val jobSeekers: List<JobSeeker> = emptyList(),
    val jobOffers: List<JobOffer> = emptyList(),
    val banners: List<Banner> = emptyList(),
)
