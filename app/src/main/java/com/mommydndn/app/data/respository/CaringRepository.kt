package com.mommydndn.app.data.respository

import android.net.Uri
import androidx.paging.PagingData
import com.mommydndn.app.data.api.model.response.CreateJobOfferResponse
import com.mommydndn.app.data.api.model.response.JobOfferResponse
import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeItem
import com.mommydndn.app.data.model.care.EtcCheckItem
import com.mommydndn.app.data.model.care.JobOffer
import com.mommydndn.app.data.model.care.JobOfferSummary
import com.mommydndn.app.data.model.care.JobSeeker
import com.mommydndn.app.data.model.care.MinHourlySalary
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.care.WorkPeriodType
import com.mommydndn.app.data.model.common.DayOfWeekItem
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

    fun fetchJobOfferSummary(): Flow<PagingData<JobOfferSummary>>

    fun fetchCaringTypeItems(): Flow<List<CaringTypeItem>>

    fun fetchMinHourlySalary(): Flow<MinHourlySalary>

    fun createJobOffer(
        title: String,
        content: String,
        caringTypeList: List<CaringType>,
        taskType: WorkPeriodType,
        startDate: LocalDate?,
        endDate: LocalDate?,
        days: List<DayOfWeekItem>,
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