package com.mommydndn.app.data.respository

import androidx.paging.PagingData
import com.mommydndn.app.data.model.JobOffer
import com.mommydndn.app.data.model.JobOfferSummary
import com.mommydndn.app.data.model.JobSeeker
import com.mommydndn.app.data.model.LocationInfo
import kotlinx.coroutines.flow.Flow

interface CaringRepository {
    fun fetchNearestJobSeeker(): Flow<List<JobSeeker>>

    fun fetchNearestJobOffer(): Flow<List<JobOffer>>

    fun fetchJobOfferSummary(): Flow<PagingData<JobOfferSummary>>

}