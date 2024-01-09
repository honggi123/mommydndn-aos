package com.mommydndn.app.ui.care

import com.mommydndn.app.domain.model.user.Neighborhood
import com.mommydndn.app.ui.care.filter.CareFilters
import com.mommydndn.app.ui.care.filter.CareOrderBy
import com.mommydndn.app.ui.care.job.CareJobOpeningUiModel

// todo: naming, filter selected, user canceled update filter ?
sealed interface CareUiState {

    data object Loading : CareUiState

    data class Success(
        val neighborhood: Neighborhood,
        val order: CareOrderBy,
        val filters: List<CareFilters<*>>,
        val jobOpeningListItems: List<CareJobOpeningUiModel>,
        // todo: care_provider_list, agency_list
    ) : CareUiState

    data class Failure(
        val exception: Exception
    ) : CareUiState
}