package com.mommydndn.app.data.respository

import androidx.paging.PagingData
import com.mommydndn.app.data.api.model.CompanyEtcCheckItem
import com.mommydndn.app.data.api.model.IndividualEtcCheckItem
import com.mommydndn.app.data.model.EtcCheckItem
import com.mommydndn.app.data.model.JobOffer
import com.mommydndn.app.data.model.JobOfferSummary
import com.mommydndn.app.data.model.JobSeeker
import com.mommydndn.app.data.model.LocationInfo
import com.mommydndn.app.data.model.UserType
import kotlinx.coroutines.flow.Flow

interface CaringRepository {
    fun fetchNearestJobSeeker(): Flow<List<JobSeeker>>

    fun fetchNearestJobOffer(): Flow<List<JobOffer>>

    fun fetchEtcCheckList(userType: UserType): Flow<List<EtcCheckItem>>

    fun fetchJobOfferSummary(): Flow<PagingData<JobOfferSummary>>

}