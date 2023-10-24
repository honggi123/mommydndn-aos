package com.mommydndn.app.data.respository

import android.net.Uri
import androidx.paging.PagingData
import com.mommydndn.app.data.api.model.response.CaringTypeResponse
import com.mommydndn.app.data.model.care.CaringTypeItem
import com.mommydndn.app.data.model.care.EtcCheckItem
import com.mommydndn.app.data.model.care.JobOffer
import com.mommydndn.app.data.model.care.JobOfferSummary
import com.mommydndn.app.data.model.care.JobSeeker
import com.mommydndn.app.data.model.care.MinHourlySalary
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.care.WorkHoursType
import com.mommydndn.app.data.model.common.DayOfWeekItem
import com.mommydndn.app.data.model.map.EmdItem
import com.mommydndn.app.data.model.user.UserType
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface CaringRepository {
    fun fetchNearestJobSeeker(): Flow<List<JobSeeker>>

    fun fetchNearestJobOffer(): Flow<List<JobOffer>>

    fun fetchEtcIndividualCheckList(): Flow<List<EtcCheckItem>>

    fun fetchJobOfferSummary(): Flow<PagingData<JobOfferSummary>>

    fun fetchCaringTypeItems(): Flow<List<CaringTypeItem>>

    fun fetchMinHourlySalary(): Flow<MinHourlySalary>

    fun createJobOffer(
        title: String,
        content: String,
        caringTypeIdList: List<Int>,
        taskType: WorkHoursType,
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
        imageList: List<Uri>,
        onSuccess: () -> Unit
    ): Flow<Unit>
}