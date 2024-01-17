package com.mommydndn.app.ui.care

import com.mommydndn.app.ui.care.filter.CareFilters
import com.mommydndn.app.ui.care.filter.CareOrderBy
import com.mommydndn.app.ui.care.job.CareJobUiModel

// todo: naming, filter selected, user canceled update filter ?
sealed interface CareUiState {

    data object Loading : CareUiState

    data class Success(
        val neighborhood: NeighborhoodUiModel,
        val order: CareOrderBy,
        val filters: List<CareFilters<*>>,
        val jobs: List<CareJobUiModel>,
        // val workers: List<CareWorkerUiModel>
        // todo: care_provider_list, agency_list
    ) : CareUiState

    data class Failure(
        val exception: Exception
    ) : CareUiState
}