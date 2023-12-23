package com.mommydndn.app.domain.repository

import androidx.paging.PagingData // from paging-common: without Android dependencies
import com.mommydndn.app.domain.model.care.CareJobOpening
import kotlinx.coroutines.flow.Flow

interface CareRepository {

    fun getNearbyCareJobOpenings(latitude: Double, longitude: Double): Flow<PagingData<CareJobOpening>>
}