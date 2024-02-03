package com.mommydndn.app.data.repository

import androidx.paging.PagingData
import com.mommydndn.app.data.mapper.mapToDomainCaregiverJobApplications
import com.mommydndn.app.data.network.service.CareService
import com.mommydndn.app.domain.model.CaregiverJobApplication
import com.mommydndn.app.domain.repository.CaregiverJobApplicationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CaregiverJobApplicationDataRepository @Inject constructor(
    private val careService: CareService
) : CaregiverJobApplicationRepository {

    override fun getNearbyCareWorkers(): Flow<PagingData<CaregiverJobApplication>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCareWorkerDetails(id: Long): CaregiverJobApplication {
        TODO("Not yet implemented")
    }

    override suspend fun postCareWorkerProfile() {
        TODO("Not yet implemented")
    }

    override suspend fun updateCareWorkerProfile() {
        TODO("Not yet implemented")
    }

    override suspend fun getNearbytCaregiverJobApplication(): List<CaregiverJobApplication> {
        return careService.getNearbyCareWorkers()
            .mapToDomainCaregiverJobApplications()
    }

}