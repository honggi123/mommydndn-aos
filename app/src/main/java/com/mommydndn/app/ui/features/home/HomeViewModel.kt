package com.mommydndn.app.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.data.api.model.response.BabyItem
import com.mommydndn.app.data.api.model.response.BabyItemMeta
import com.mommydndn.app.data.model.notification.Notification
import com.mommydndn.app.domain.repository.BabyItemRepository
import com.mommydndn.app.domain.repository.CaringRepository
import com.mommydndn.app.domain.repository.CommonRepositoy
import com.mommydndn.app.domain.repository.NotificationRepository
import com.mommydndn.app.domain.usecase.caring.GetNearestJobOffersUseCase
import com.mommydndn.app.domain.usecase.caring.GetNearestJobSeekersUseCase
import com.mommydndn.app.domain.usecase.notification.GetAllNotificationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

const val INITIAL_BABY_ITEM_PAGE_SIZE = 10
const val MORE_BABY_ITEM_PAGE_SIZE = 10

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val babyItemRepository: BabyItemRepository,
    private val getNearestJobOffersUseCase: GetNearestJobOffersUseCase,
    private val getNearestJobSeekersUseCase: GetNearestJobSeekersUseCase,
    private val getAllNotificationUseCase: GetAllNotificationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = false))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _babyItems: MutableStateFlow<List<BabyItem>> = MutableStateFlow(emptyList())
    val babyItems: StateFlow<List<BabyItem>> = _babyItems

    private val _babyItemsPagingMeta: MutableStateFlow<BabyItemMeta> =
        MutableStateFlow(BabyItemMeta(totalCount = 0, currentPageNum = 1, requestTimestamp = 0))
    val babyItemsPagingMeta: StateFlow<BabyItemMeta> = _babyItemsPagingMeta

    init {
        refreshAll()

        fetchBabyItems(
            pageNum = _babyItemsPagingMeta.value.currentPageNum,
            pageSize = INITIAL_BABY_ITEM_PAGE_SIZE
        )
    }

    private fun refreshAll() {

        viewModelScope.launch {
            val notificationDeffered = async { getAllNotificationUseCase.invoke(Unit) }
            val jobSeekerseffered = async { getNearestJobSeekersUseCase.invoke(Unit) }
            val jobOffersDeffered =async { getNearestJobOffersUseCase.invoke(Unit) }

            val jobSeekers = jobSeekerseffered.await()
            val jobOffers = jobOffersDeffered.await()
            val notifications = notificationDeffered.await()

            _uiState.update {
                it.copy(
                    jobOffers = jobOffers,
                    jobSeekers = jobSeekers,
                    notifications = notifications
                )
            }
        }

    }


    private fun fetchBabyItems(pageNum: Int, pageSize: Int) {
        viewModelScope.launch {
            babyItemRepository.fetchNearestBabyItemSummary(
                pageNum,
                pageSize,
                System.currentTimeMillis()
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