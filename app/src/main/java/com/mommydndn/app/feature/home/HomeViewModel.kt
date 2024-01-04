package com.mommydndn.app.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.domain.usecase.care.GetBabyItemsUseCase
import com.mommydndn.app.domain.usecase.care.GetNearestJobOffersUseCase
import com.mommydndn.app.domain.usecase.care.GetNearestJobSeekersUseCase
import com.mommydndn.app.domain.usecase.common.GetBannersUseCase
import com.mommydndn.app.domain.usecase.notification.GetNotificationsUseCase
import com.mommydndn.app.utils.result.data
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// const val MAX_BABY_ITEM_PAGES = 4
// const val INITIAL_BABY_ITEM_SIZE = 10
// const val MORE_BABY_ITEM_SIZE = 10

class HomeViewModel constructor(
    private val getBabyItemsUseCase: GetBabyItemsUseCase,
    private val getBannersUseCase: GetBannersUseCase,
    private val getNearestJobOffersUseCase: GetNearestJobOffersUseCase,
    private val getNearestJobSeekersUseCase: GetNearestJobSeekersUseCase,
    private val getNotificationsUseCase: GetNotificationsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        initAll()
    }

    private fun initAll() {
        viewModelScope.launch {

            val bannersDeffered = async { getBannersUseCase.invoke(Unit) }
            val notificationDeffered = async { getNotificationsUseCase.invoke(Unit) }
            val jobSeekerDeffered = async { getNearestJobSeekersUseCase.invoke(Unit) }
            val jobOffersDeffered = async { getNearestJobOffersUseCase.invoke(Unit) }

            val banners = bannersDeffered.await().data ?: emptyList()
            val jobSeekers = jobSeekerDeffered.await().data ?: emptyList()
            val jobOffers = jobOffersDeffered.await().data ?: emptyList()
            val notifications = notificationDeffered.await().data ?: emptyList()

            _uiState.update {
                HomeUiState.Success(
//                    banners = banners,
//                    jobOffers = jobOffers,
//                    jobSeekers = jobSeekers,
//                    notifications = notifications.filter { !it.isApproved }
                )
            }
        }
    }

//    fun fetchMoreBabyItems(currentPage: Int) {
//        fetchBabyItems(
//            pageNum = currentPage + 1,
//            pageSize = MORE_BABY_ITEM_SIZE
//        )
//    }

//    private fun fetchBabyItems(pageNum: Int, pageSize: Int) {
//        viewModelScope.launch {
//            getBabyItemsUseCase(
//                GetBabyItemsParams(
//                    pageNum,
//                    pageSize,
//                    System.currentTimeMillis()
//                )
//            ).collect { result ->
//                when (result) {
//                    is Result.Success -> {
//                        _uiState.update { state ->
//                            if (state is HomeUiState.Success) {
//                                state.copy(
//                                    babyItemUiState = HomeBabyItemUiState.Success(
//                                        babyItems = result.data.itemSummaryList + result.data.itemSummaryList,
//                                        babyItemsPagingMeta = result.data.meta
//                                    )
//                                )
//                            } else {
//                                state
//                            }
//                        }
//                    }
//
//                    is Result.Loading -> {
//                        _uiState.update { state ->
//                            if (state is HomeUiState.Success) {
//                                state.copy(
//                                    babyItemUiState = HomeBabyItemUiState.Loading(
//                                        babyItems = state.babyItemUiState.babyItems
//                                    )
//                                )
//                            } else {
//                                state
//                            }
//                        }
//                    }
//
//                    is Result.Failure -> {
//                        // TODO
//                    }
//                }
//            }
//        }
//    }


}