package com.mommydndn.app.ui.features.care

import com.mommydndn.app.domain.model.user.Neighborhood

sealed interface CareUiState {

    object Loading : CareUiState

    data class Success(
        val neighborhood: Neighborhood,
        val order: CareOrderBy,
        val filters: List<CareFilter>,
    ) : CareUiState

    data class Failure(
        val exception: Exception
    ) : CareUiState

    // todo: filter selected, user canceled update filter ?
}