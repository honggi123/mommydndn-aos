package com.mommydndn.app.data.respository

import androidx.paging.PagingData
import com.mommydndn.app.data.api.model.response.CaringTypeResponse
import com.mommydndn.app.data.model.care.CaringTypeItem
import com.mommydndn.app.data.model.care.EtcCheckItem
import com.mommydndn.app.data.model.care.JobOffer
import com.mommydndn.app.data.model.care.JobOfferSummary
import com.mommydndn.app.data.model.care.JobSeeker
import com.mommydndn.app.data.model.care.MinHourlySalary
import com.mommydndn.app.data.model.user.UserType
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow

interface CaringRepository {
    fun fetchNearestJobSeeker(): Flow<List<JobSeeker>>

    fun fetchNearestJobOffer(): Flow<List<JobOffer>>

    fun fetchEtcIndividualCheckList(): Flow<List<EtcCheckItem>>

    fun fetchJobOfferSummary(): Flow<PagingData<JobOfferSummary>>

    fun fetchCaringTypeItems(): Flow<List<CaringTypeItem>>

    fun fetchMinHourlySalary() : Flow<MinHourlySalary>
}