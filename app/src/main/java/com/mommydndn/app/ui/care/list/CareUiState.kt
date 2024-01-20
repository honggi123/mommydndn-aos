package com.mommydndn.app.ui.care.list

import com.mommydndn.app.ui.care.list.filter.CareFilter
import com.mommydndn.app.ui.care.list.filter.CareOrderBy

// todo: naming, filter selected, user canceled update filter ?
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