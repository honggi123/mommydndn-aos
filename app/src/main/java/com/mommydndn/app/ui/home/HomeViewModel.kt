package com.mommydndn.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mommydndn.app.domain.usecase.care.GetNearbyCareJobOpeningsUseCase
import com.mommydndn.app.domain.usecase.care.GetNearestCareProvidersUseCase
import com.mommydndn.app.domain.usecase.common.GetBannersUseCase
import com.mommydndn.app.domain.usecase.notification.GetNotificationsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getNotificationsUseCase: GetNotificationsUseCase,
    private val getBannersUseCase: GetBannersUseCase,
    private val getNearestCareProvidersUseCase: GetNearestCareProvidersUseCase,
    private val getNearbyCareJobOpeningsUseCase: GetNearbyCareJobOpeningsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {

        }
    }
}