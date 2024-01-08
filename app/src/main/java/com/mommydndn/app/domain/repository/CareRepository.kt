package com.mommydndn.app.domain.repository

import androidx.paging.PagingData
import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeItem
import com.mommydndn.app.data.model.care.EtcCheckItem
import com.mommydndn.app.data.network.feature.care.response.GetMinHourlySalaryResponse
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.care.SortingType
import com.mommydndn.app.data.model.care.WorkPeriodType
import com.mommydndn.app.data.network.feature.care.response.CompanySummaryListItem
import com.mommydndn.app.data.network.feature.care.response.JobOfferSummaryListItem
import com.mommydndn.app.data.network.feature.care.response.JobSeekerSummaryItem
import com.mommydndn.app.data.model.common.DayOfWeekItem
import com.mommydndn.app.data.model.common.DayOfWeekType
import com.mommydndn.app.data.network.feature.care.response.CreateAgencyCareProviderResponse
import com.mommydndn.app.data.network.feature.care.response.CreateJobOpeningResponse
import com.mommydndn.app.data.network.feature.care.JobOfferResponse
import com.mommydndn.app.data.network.feature.care.response.CreateCareProviderResponse
import com.mommydndn.app.domain.model.care.JobOffer
import com.mommydndn.app.domain.model.care.JobSeeker
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import java.time.LocalDate
import java.time.LocalTime

interface CareRepository {

    fun fetchJobOffer(jobOfferId: Int): Flow<JobOfferResponse>

    suspend fun fetchNearestJobSeekers(): List<JobSeeker>

    suspend fun fetchNearestJobOffers(): List<JobOffer>

    fun fetchEtcIndividualCheckList(): Flow<List<EtcCheckItem>>

    fun fetchCompanyEtcCheckList(): Flow<List<EtcCheckItem>>

    fun fetchJobOfferSummary(
        keyword: String?,
        sortingType: SortingType,
        emdId: Int,
        neighborhoodScope: Int,
        caringTypeList: List<CaringType>,
        days: List<DayOfWeekType>,
        startTime: LocalTime?,
        endTime: LocalTime?,
        workPeriodType: WorkPeriodType?
    ): Flow<PagingData<JobOfferSummaryListItem>>

    fun fetchJobSeekerSummary(
        keyword: String?,
        sortingType: SortingType,
        emdId: Int,
        neighborhoodScope: Int,
        caringTypeList: List<CaringType>,
    ): Flow<PagingData<JobSeekerSummaryItem>>

    fun fetchCompanySummary(
        keyword: String?,
        sortingType: SortingType,
        emdId: Int,
        neighborhoodScope: Int,
        caringTypeList: List<CaringType>,
    ): Flow<PagingData<CompanySummaryListItem>>

    fun fetchCaringTypeItems(): Flow<List<CaringTypeItem>>

    fun fetchMinHourlySalary(): Flow<GetMinHourlySalaryResponse>

    fun createJobOffer(
        title: String,
        content: String,
        caringTypeList: List<CaringType>,
        taskType: WorkPeriodType,
        dateList: List<LocalDate>?,
        days: List<DayOfWeekItem>,
        startDate: LocalDate?,
        endDate: LocalDate?,
        startTime: LocalTime?,
        endTime: LocalTime?,
//        emd: EmdItem,
        latitude: Double,
        longitude: Double,
        salaryType: SalaryType,
        salary: Int,
        etcCheckedList: List<EtcCheckItem>,
        imageList: List<MultipartBody.Part>,
    ): Flow<com.mommydndn.app.data.network.feature.care.response.CreateJobOpeningResponse>

    fun createJobSeeker(
        introduce: String,
        caringTypeList: List<CaringType>,
//        emd: EmdItem,
        latitude: Double?,
        longitude: Double?,
        salaryType: SalaryType,
        salary: Int,
        etcCheckedList: List<EtcCheckItem>
    ): Flow<com.mommydndn.app.data.network.feature.care.response.CreateCareProviderResponse>

    fun createCompany(
        introduce: String,
        coverImageList: List<MultipartBody.Part>,
        caringTypeList: List<CaringType>,
//        emd: EmdItem,
        latitude: Double?,
        longitude: Double?,
        minSalary: Int,
        maxSalary: Int,
        etcCheckedList: List<EtcCheckItem>,
        commission: Int
    ): Flow<com.mommydndn.app.data.network.feature.care.response.CreateAgencyCareProviderResponse>
}