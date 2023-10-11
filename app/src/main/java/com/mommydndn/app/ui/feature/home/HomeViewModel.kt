package com.mommydndn.app.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mommydndn.app.data.model.BabyItem
import com.mommydndn.app.data.model.JobOfferSummary
import com.mommydndn.app.data.model.NoticeSetting
import com.mommydndn.app.data.respository.BabyItemRepository
import com.mommydndn.app.data.respository.CaringRepository
import com.mommydndn.app.data.respository.CommonRepositoy
import com.mommydndn.app.data.respository.NoticeRepository
import com.mommydndn.app.utils.TimeUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

const val INITIAL_BABY_ITEM_PAGE_SIZE = 6
const val MORE_BABY_ITEM_PAGE_SIZE = 20

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

    private val _babyItems: MutableStateFlow<List<BabyItem>> = MutableStateFlow(emptyList())
    val babyItems: StateFlow<List<BabyItem>> = _babyItems

    private val _moreBabyItemClickedCount: MutableStateFlow<Int> = MutableStateFlow(1)
    val moreBabyItemClickedCount: StateFlow<Int> = _moreBabyItemClickedCount

    init {
        fetchBabyItems(
            pageNum = _moreBabyItemClickedCount.value,
            pageSize = INITIAL_BABY_ITEM_PAGE_SIZE
        )
    }

    private fun filteredNoticeSettings(): Flow<List<NoticeSetting>> =
        noticeRepository.fetchUserNoticeSettings().map { noticeSettings ->
            noticeSettings.filter { !it.isApproved }
        }

    private fun fetchBabyItems(pageNum: Int, pageSize: Int) {
        viewModelScope.launch {
            babyItemRepository.fetchNearestBabyItem(pageNum, pageSize).map { list ->
                list.map { it.copy(createdAt = TimeUtils.formatTimeAgo(it.createdAt.toLong())) }
            }.collect { newItems ->
                val currentItems = _babyItems.value
                val combinedList = currentItems + newItems
                _babyItems.value = combinedList
            }
        }
    }

    fun fetchMoreBabyItems(currentCount: Int) {
        _moreBabyItemClickedCount.value = currentCount + 1
        fetchBabyItems(
            pageNum = _moreBabyItemClickedCount.value,
            pageSize = MORE_BABY_ITEM_PAGE_SIZE
        )
    }


}