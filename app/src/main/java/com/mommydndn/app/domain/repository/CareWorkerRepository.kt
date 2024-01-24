package com.mommydndn.app.domain.repository

import androidx.paging.PagingData
import com.mommydndn.app.domain.model.CareWorker
import com.mommydndn.app.domain.model.CareWorkerDetails
import kotlinx.coroutines.flow.Flow

interface CareWorkerRepository {

    fun getNearbyCareWorkers(): Flow<PagingData<CareWorker>>

    suspend fun getCareWorkerDetails(id: Long): CareWorkerDetails

    suspend fun postCareWorkerProfile()

    suspend fun updateCareWorkerProfile()

    suspend fun getNearestCareWorkers()
}