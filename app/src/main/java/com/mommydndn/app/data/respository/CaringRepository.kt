package com.mommydndn.app.data.respository

import androidx.paging.PagingData
import com.mommydndn.app.data.model.care.EtcCheckItem
import com.mommydndn.app.data.model.care.JobOffer
import com.mommydndn.app.data.model.care.JobOfferSummary
import com.mommydndn.app.data.model.care.JobSeeker
import com.mommydndn.app.data.model.user.UserType
import kotlinx.coroutines.flow.Flow

interface CaringRepository {
    fun fetchNearestJobSeeker(): Flow<List<JobSeeker>>

    fun fetchNearestJobOffer(): Flow<List<JobOffer>>

    fun fetchEtcCheckList(userType: UserType): Flow<List<EtcCheckItem>>

    fun fetchJobOfferSummary(): Flow<PagingData<JobOfferSummary>>

}