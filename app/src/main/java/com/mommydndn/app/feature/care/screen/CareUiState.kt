package com.mommydndn.app.feature.care.screen

import com.mommydndn.app.domain.model.user.Neighborhood
import com.mommydndn.app.feature.care.filters.CareFilter
import com.mommydndn.app.feature.care.filters.CareOrderBy
import com.mommydndn.app.feature.care.jobopening.CareJobOpeningListItem

// todo: naming, filter selected, user canceled update filter ?
sealed interface CareUiState {

    object Loading : CareUiState

    data class Success(
        val neighborhood: Neighborhood,
        val order: CareOrderBy,
        val filters: List<CareFilter<*>>,
        val jobOpeningListItems: List<CareJobOpeningListItem>,
        // todo: care_provider_list, agency_list
    ) : CareUiState

    data class Failure(
        val exception: Exception
    ) : CareUiState
}