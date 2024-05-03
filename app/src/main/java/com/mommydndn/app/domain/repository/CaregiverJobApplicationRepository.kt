package com.mommydndn.app.domain.repository

import androidx.paging.PagingData
import com.mommydndn.app.domain.model.CaregiverJobApplication
import kotlinx.coroutines.flow.Flow

interface CaregiverJobApplicationRepository {

    fun getNearbyCareWorkers(): Flow<PagingData<CaregiverJobApplication>>

    suspend fun getCareWorkerDetails(id: Long): CaregiverJobApplication

    suspend fun postCareWorkerProfile()

    suspend fun updateCareWorkerProfile()

    suspend fun getNearbyCaregiverJobApplication() : List<CaregiverJobApplication>
}