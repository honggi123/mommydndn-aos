package com.mommydndn.app.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.data.api.model.BabyItem
import com.mommydndn.app.data.api.model.Meta
import com.mommydndn.app.data.model.NoticeSetting
import com.mommydndn.app.data.respository.BabyItemRepository
import com.mommydndn.app.data.respository.CaringRepository
import com.mommydndn.app.data.respository.CommonRepositoy
import com.mommydndn.app.data.respository.NoticeRepository
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

    private val _babyItemsPagingMeta: MutableStateFlow<Meta> =
        MutableStateFlow(Meta(totalCount = 0, currentPageNum = 1, requestTimestamp = 0))
    val babyItemsPagingMeta: StateFlow<Meta> = _babyItemsPagingMeta

    init {
        fetchBabyItems(
            pageNum = _babyItemsPagingMeta.value.currentPageNum,
            pageSize = INITIAL_BABY_ITEM_PAGE_SIZE
        )
    }

    private fun filteredNoticeSettings(): Flow<List<NoticeSetting>> =
        noticeRepository.fetchUserNoticeSettings().map { noticeSettings ->
            noticeSettings.filter { !it.isApproved }
        }

    private fun fetchBabyItems(pageNum: Int, pageSize: Int) {
        viewModelScope.launch {
            babyItemRepository.fetchNearestBabyItemSummary(
                pageNum,
                pageSize,
                System.currentTimeMillis() / 1000
            ).collect { item ->
                val currentItems = _babyItems.value

                val combinedList = currentItems + item.itemSummaryList

                _babyItemsPagingMeta.value = item.meta
                _babyItems.value = combinedList
            }
        }
    }

    fun fetchMoreBabyItems(currentCount: Int) {
        fetchBabyItems(
            pageNum = currentCount + 1,
            pageSize = MORE_BABY_ITEM_PAGE_SIZE
        )
    }


}