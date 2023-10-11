package com.mommydndn.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mommydndn.app.data.model.JobOfferSummary
import com.mommydndn.app.data.model.NoticeSetting
import com.mommydndn.app.data.respository.BabyItemRepository
import com.mommydndn.app.data.respository.CaringRepository
import com.mommydndn.app.data.respository.CommonRepositoy
import com.mommydndn.app.data.respository.NoticeRepository
import com.mommydndn.app.utils.TimeUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noticeRepository: NoticeRepository,
    private val caringRepository: CaringRepository,
    private val babyItemRepository: BabyItemRepository,
    private val commonRepositoy: CommonRepositoy
) : ViewModel() {

    val noticeSettings = filteredNoticeSettings().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    val banners = commonRepositoy.fetchBanners().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    val jobSeekers = caringRepository.fetchNearestJobSeeker().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    val jobOffers = caringRepository.fetchNearestJobOffer().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    val babyItems = babyItemRepository.fetchNearestBabyItem().map { list ->
        list.map { it.copy(createdAt = TimeUtils.formatTimeAgo(it.createdAt.toLong())) }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )


    private fun filteredNoticeSettings(): Flow<List<NoticeSetting>> =
        noticeRepository.fetchUserNoticeSettings().map { noticeSettings ->
            noticeSettings.filter { !it.isApproved }
        }



}