package com.mommydndn.app.ui.care.list

import androidx.lifecycle.ViewModel
import com.mommydndn.app.domain.usecase.care.GetNearbyCareJobOpeningsUseCase
import com.mommydndn.app.domain.usecase.user.GetNearbyNeighborhoodDistanceUseCase
import com.mommydndn.app.domain.usecase.user.GetNearbyNeighborhoodsUseCase
import com.mommydndn.app.domain.usecase.user.GetNeighborhoodUseCase
import com.mommydndn.app.ui.care.list.agency.CareAgencyUiModel
import com.mommydndn.app.ui.care.list.filter.CareFilter
import com.mommydndn.app.ui.care.list.filter.CareOrderBy
import com.mommydndn.app.ui.care.list.job.CareJobUiModel
import com.mommydndn.app.ui.care.list.worker.CareWorkerUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

// TODO
@HiltViewModel
class CareViewModel @Inject constructor(
    getNeighborhoodUseCase: GetNeighborhoodUseCase,
    getNearbyNeighborhoodDistanceUseCase: GetNearbyNeighborhoodDistanceUseCase,
    getNearbyNeighborhoodsUseCase: GetNearbyNeighborhoodsUseCase,
    private val getNearbyCareJobOpeningsUseCase: GetNearbyCareJobOpeningsUseCase,
) : ViewModel() {

    private val orderBy = MutableStateFlow(CareOrderBy.LATEST)

    fun setOrderBy(orderBy: CareOrderBy) {
        this.orderBy.value = orderBy
    }
}

sealed interface CareUiState {

    data object Loading : CareUiState

    data class Success(
        val neighborhood: NeighborhoodUiModel,
        val orderBy: CareOrderBy,
        val filters: List<CareFilter>,
        val jobs: List<CareJobUiModel>,
        val workers: List<CareWorkerUiModel>,
        val agencies: List<CareAgencyUiModel>,
    ) : CareUiState

    data class Failure(val exception: Exception) : CareUiState
}