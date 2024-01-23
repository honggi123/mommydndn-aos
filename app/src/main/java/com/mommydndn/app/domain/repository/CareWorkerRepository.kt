package com.mommydndn.app.domain.repository

import androidx.paging.PagingData
import com.mommydndn.app.domain.model.CareWorker
import kotlinx.coroutines.flow.Flow

interface CareWorkerRepository {

    fun getCareWorkers(): Flow<PagingData<CareWorker>>

    suspend fun getCareWorkerDetails(id: Long)

    suspend fun postCareWorkerProfile()

    suspend fun updateCareWorkerProfile()

    suspend fun getNearestCareWorkers()
}