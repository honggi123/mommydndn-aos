package com.mommydndn.app.domain.repository

import com.mommydndn.app.domain.model.CaregiverJobPosting

interface CaregiverJobPostingRepository {

    suspend fun getNearbyCaregiverJobPostings(): List<CaregiverJobPosting>
}