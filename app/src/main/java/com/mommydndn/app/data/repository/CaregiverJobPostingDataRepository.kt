package com.mommydndn.app.data.repository

import com.mommydndn.app.data.network.service.CareService
import com.mommydndn.app.domain.model.CaregiverJobPosting
import com.mommydndn.app.domain.repository.CaregiverJobPostingRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CaregiverJobPostingDataRepository @Inject constructor(
    private val careService: CareService
) : CaregiverJobPostingRepository {

    override suspend fun getNearbyCaregiverJobPostings(): List<CaregiverJobPosting> {
       TODO()
    }
}