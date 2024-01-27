package com.mommydndn.app.ui.care.list

import androidx.lifecycle.ViewModel
import com.mommydndn.app.ui.care.list.agency.CareAgencyUiModel
import com.mommydndn.app.ui.care.list.filter.CareFilter
import com.mommydndn.app.ui.care.list.filter.CareOrderBy
import com.mommydndn.app.ui.care.list.jobapplication.CaregiverJobApplicationUiModel
import com.mommydndn.app.ui.care.list.jobposting.CaregiverJobPostingUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CareViewModel @Inject constructor() : ViewModel() {

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
        val jobs: List<CaregiverJobPostingUiModel>,
        val workers: List<CaregiverJobApplicationUiModel>,
        val agencies: List<CareAgencyUiModel>,
    ) : CareUiState

    data class Failure(val exception: Exception) : CareUiState
}