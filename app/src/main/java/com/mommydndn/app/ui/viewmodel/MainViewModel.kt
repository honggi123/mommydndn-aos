package com.mommydndn.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.data.model.NoticeSetting
import com.mommydndn.app.data.model.TermsItem
import com.mommydndn.app.data.respository.AccountRepository
import com.mommydndn.app.data.respository.CaringRepository
import com.mommydndn.app.data.respository.CommonRepositoy
import com.mommydndn.app.data.respository.NoticeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

@HiltViewModel
class MainViewModel @Inject constructor(
    private val noticeRepository: NoticeRepository,
    private val caringRepository: CaringRepository,
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

    private fun filteredNoticeSettings(): Flow<List<NoticeSetting>> =
        noticeRepository.fetchUserNoticeSettings(
        ).map { noticeSettings ->
            noticeSettings.filter { !it.isApproved }
        }


}