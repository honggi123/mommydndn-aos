package com.mommydndn.app.data.respository

import androidx.paging.PagingData
import com.mommydndn.app.data.api.model.request.JobOfferListRequest
import com.mommydndn.app.data.api.model.response.CreateJobOfferResponse
import com.mommydndn.app.data.api.model.response.JobOfferResponse
import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeItem
import com.mommydndn.app.data.model.care.EtcCheckItem
import com.mommydndn.app.data.model.care.Filter.FilterType
import com.mommydndn.app.data.model.care.JobOffer
import com.mommydndn.app.data.model.care.JobOfferSummaryListItem
import com.mommydndn.app.data.model.care.JobSeeker
import com.mommydndn.app.data.model.care.MinHourlySalary
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.care.SortingType
import com.mommydndn.app.data.model.care.WorkPeriodType
import com.mommydndn.app.data.model.common.DayOfWeekItem
import com.mommydndn.app.data.model.common.DayOfWeekType
import com.mommydndn.app.data.model.map.EmdItem
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import java.time.LocalDate
import java.time.LocalTime

interface CaringRepository {

    fun fetchJobOffer(jobOfferId: Int): Flow<JobOfferResponse>

    fun fetchNearestJobSeeker(): Flow<List<JobSeeker>>

    fun fetchNearestJobOffer(): Flow<List<JobOffer>>

    fun fetchEtcIndividualCheckList(): Flow<List<EtcCheckItem>>

    fun fetchJobOfferSummary(
        keyword: String?,
        sortingType: SortingType,
        emdId: Int,
        neighborhoodScope: Int,
        caringTypeList: List<CaringType>,
        days:List<DayOfWeekType>,
        startTime: LocalTime?,
        endTime: LocalTime?,
        workPeriodTypeList: List<WorkPeriodType>
    ): Flow<PagingData<JobOfferSummaryListItem>>

    fun fetchCaringTypeItems(): Flow<List<CaringTypeItem>>

    fun fetchMinHourlySalary(): Flow<MinHourlySalary>

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
        emd: EmdItem,
        latitude: Double,
        longitude: Double,
        salaryType: SalaryType,
        salary: Int,
        etcCheckedList: List<EtcCheckItem>,
        imageList: List<MultipartBody.Part>,
    ): Flow<CreateJobOfferResponse>
}