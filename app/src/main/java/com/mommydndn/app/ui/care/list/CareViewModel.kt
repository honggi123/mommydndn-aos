package com.mommydndn.app.ui.care.list

import androidx.lifecycle.ViewModel
import com.mommydndn.app.domain.usecase.care.GetNearbyCareJobOpeningsUseCase
import com.mommydndn.app.domain.usecase.user.GetNearbyNeighborhoodDistanceUseCase
import com.mommydndn.app.domain.usecase.user.GetNearbyNeighborhoodsUseCase
import com.mommydndn.app.domain.usecase.user.GetNeighborhoodUseCase
import com.mommydndn.app.ui.care.list.filter.CareOrderBy
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