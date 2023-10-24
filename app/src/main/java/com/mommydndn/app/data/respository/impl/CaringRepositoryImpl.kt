package com.mommydndn.app.data.respository.impl

import android.net.Uri
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mommydndn.app.data.api.model.request.JobOfferRequest
import com.mommydndn.app.data.api.service.CaringService
import com.mommydndn.app.data.api.service.CommonService
import com.mommydndn.app.data.datasource.pagingsource.JobOfferSummaryPagingSource
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
import com.mommydndn.app.data.respository.CaringRepository
import com.mommydndn.app.utils.DateTimeUtils
import com.mommydndn.app.utils.MediaFileUtil
import com.skydoves.sandwich.getOrElse
import com.skydoves.sandwich.getOrNull
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

class CaringRepositoryImpl @Inject constructor(
    private val caringService: CaringService,
    private val commonService: CommonService
) : CaringRepository {
    override fun fetchNearestJobSeeker(): Flow<List<JobSeeker>> = flow {
        caringService.fetchNearestJobSeeker().suspendOnSuccess {
            emit(data)
        }
    }.flowOn(Dispatchers.IO)

    override fun fetchNearestJobOffer(): Flow<List<JobOffer>> = flow {
        caringService.fetchNearestJobOffer().suspendOnSuccess {
            val list = data.map {
                JobOffer(
                    title = it.title,
                    neighborhood = it.neighborhood,
                    salary = it.salary,
                    salaryType = it.salaryTypeCode,
                    caringType = it.caringTypeCode
                )
            }
            emit(list)
        }
    }.flowOn(Dispatchers.IO)

    override fun fetchEtcIndividualCheckList(): Flow<List<EtcCheckItem>> = flow {
        caringService.fetchIndividualEtcCheckList().suspendOnSuccess {
            val list = data.map {
                EtcCheckItem(
                    displayName = it.displayName,
                    id = it.indOtherConditionId,
                    conditionCode = it.indOtherConditionCode
                )
            }
            emit(list)
        }
    }.flowOn(Dispatchers.IO)


    override fun fetchJobOfferSummary(): Flow<PagingData<JobOfferSummary>> {
        return Pager(
            config = PagingConfig(
                pageSize = 15, enablePlaceholders = false
            ),
            pagingSourceFactory = { JobOfferSummaryPagingSource(caringService) }
        ).flow
    }

    override fun fetchCaringTypeItems(): Flow<List<CaringTypeItem>> = flow {
        caringService.fetchCaringTypesResponse().suspendOnSuccess {
            val list = data.map {
                CaringTypeItem(
                    caringTypeId = it.caringTypeId,
                    caringType = it.caringTypeCode,
                    displayName = it.displayName,
                )
            }
            emit(list)
        }
    }.flowOn(Dispatchers.IO)

    override fun fetchMinHourlySalary(): Flow<MinHourlySalary> = flow {
        caringService.fetchMinHourlySalary().suspendOnSuccess {
            emit(data)
        }
    }.flowOn(Dispatchers.IO)

    override fun createJobOffer(
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
    ): Flow<Unit> = flow {
        val request = JobOfferRequest(
            title = title,
            content = content,
            caringTypeIdList = caringTypeIdList,
            taskTypeCode = taskType.value,
            startDate = startDate?.let { DateTimeUtils.getTimestampByLocalDate(it) },
            endDate = endDate?.let { DateTimeUtils.getTimestampByLocalDate(it) },
            days = days.map { it.type },
            startTime = startTime?.let { DateTimeUtils.getTimestampByLocalTime(it) },
            endTime = endTime?.let { DateTimeUtils.getTimestampByLocalTime(it) },
            emd = emd,
            latitude = latitude,
            longitude = longitude,
            salaryTypeCode = salaryType,
            salary = salary,
            indOtherConditionIdList = etcCheckedList.map { it.id },
            imageIdList = fetchImagesId(imageList)
        )

        caringService.craeteJobOffer(request).suspendOnSuccess {
            emit(data)
        }
    }.flowOn(Dispatchers.IO)


    private suspend fun fetchImagesId(list: List<Uri>): List<Int> {
        return list.map { uri ->
            val imagePart = MediaFileUtil.getImagePart(uri)
            val res = commonService.fetchImageResponse(imagePart).getOrNull()
            res?.imageId ?: 0
        }
    }

}