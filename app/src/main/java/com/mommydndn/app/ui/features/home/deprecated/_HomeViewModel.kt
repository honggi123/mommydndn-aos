//package com.mommydndn.app.ui.features.home.deprecated
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.mommydndn.app.data.api.model.response.com.mommydndn.app.domain.model.care.BabyItem
//import com.mommydndn.app.data.api.model.response.com.mommydndn.app.domain.model.care.BabyItemMeta
//import com.mommydndn.app.data.model.notification.Notification
//import com.mommydndn.app.domain.repository.BabyItemRepository
//import com.mommydndn.app.domain.repository.CaringRepository
//import com.mommydndn.app.domain.repository.CommonRepositoy
//import com.mommydndn.app.domain.repository.NotificationRepository
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.stateIn
//import javax.inject.Inject
//import kotlinx.coroutines.flow.SharingStarted
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.map
//import kotlinx.coroutines.launch
//
////const val INITIAL_BABY_ITEM_PAGE_SIZE = 10
////const val MORE_BABY_ITEM_PAGE_SIZE = 10
//
//@HiltViewModel
//class HomeViewModel @Inject constructor(
//    private val notificationRepository: NotificationRepository,
//    private val caringRepository: CaringRepository,
//    private val babyItemRepository: BabyItemRepository,
//    private val commonRepositoy: CommonRepositoy
//) : ViewModel() {
//
//    val noticeSettings = filteredNoticeSettings().stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.Lazily,
//        initialValue = emptyList()
//    )
//
//    val banners = commonRepositoy.fetchBanners().stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.Lazily,
//        initialValue = emptyList()
//    )
//
//    val jobSeekers = caringRepository.fetchNearestJobSeeker().stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.Lazily,
//        initialValue = emptyList()
//    )
//
//    val jobOffers = caringRepository.fetchNearestJobOffer().stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.Lazily,
//        initialValue = emptyList()
//    )
//
//    private val _babyItems: MutableStateFlow<List<com.mommydndn.app.domain.model.care.BabyItem>> = MutableStateFlow(emptyList())
//    val babyItems: StateFlow<List<com.mommydndn.app.domain.model.care.BabyItem>> = _babyItems
//
//    private val _babyItemsPagingMeta: MutableStateFlow<com.mommydndn.app.domain.model.care.BabyItemMeta> =
//        MutableStateFlow(com.mommydndn.app.domain.model.care.BabyItemMeta(totalCount = 0, currentPageNum = 1, requestTimestamp = 0))
//    val babyItemsPagingMeta: StateFlow<com.mommydndn.app.domain.model.care.BabyItemMeta> = _babyItemsPagingMeta
//
//    init {
//        fetchBabyItems(
//            pageNum = _babyItemsPagingMeta.value.currentPageNum,
//            pageSize = INITIAL_BABY_ITEM_PAGE_SIZE
//        )
//    }
//
//    private fun filteredNoticeSettings(): Flow<List<Notification>> =
//        notificationRepository.fetchUserNoticeSettings().map { noticeSettings ->
//            noticeSettings.filter { !it.isApproved }
//        }
//
//    private fun fetchBabyItems(pageNum: Int, pageSize: Int) {
//        viewModelScope.launch {
//            babyItemRepository.fetchNearestBabyItemSummary(
//                pageNum,
//                pageSize,
//                System.currentTimeMillis()
//            ).collect { item ->
//                val currentItems = _babyItems.value
//
//                val combinedList = currentItems + item.itemSummaryList
//
//                _babyItemsPagingMeta.value = item.meta
//                _babyItems.value = combinedList
//            }
//        }
//    }
//
//    fun fetchMoreBabyItems(currentCount: Int) {
//        fetchBabyItems(
//            pageNum = currentCount + 1,
//            pageSize = MORE_BABY_ITEM_PAGE_SIZE
//        )
//    }
//
//
//}