package com.mommydndn.app.data.respository.impl

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mommydndn.app.data.api.model.request.JobOfferRequest
import com.mommydndn.app.data.api.model.response.CreateJobOfferResponse
import com.mommydndn.app.data.api.model.response.JobOfferResponse
import com.mommydndn.app.data.api.service.CaringService
import com.mommydndn.app.data.api.service.CommonService
import com.mommydndn.app.data.datasource.pagingsource.JobOfferSummaryPagingSource
import com.mommydndn.app.data.model.care.CaringType
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
import com.mommydndn.app.data.respository.CaringRepository
import com.mommydndn.app.utils.DateTimeUtils
import com.skydoves.sandwich.getOrNull
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

class CaringRepositoryImpl @Inject constructor(
    private val caringService: CaringService,
    private val commonService: CommonService
) : CaringRepository {

    override fun fetchJobOffer(jobOfferId: Int): Flow<JobOfferResponse> = flow<JobOfferResponse> {
        caringService.fetchJobOffer(jobOfferId).suspendOnSuccess {
            emit(data)
        }
    }.flowOn(Dispatchers.IO)

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
        caringTypeList: List<CaringType>,
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
        imageList: List<MultipartBody.Part>,
        onSuccess: () -> Unit
    ): Flow<CreateJobOfferResponse> = flow {

        val imageIdList = imageList.map {
            val id = fetchImageId(it) ?: 0
            id
        }

        val request = JobOfferRequest(
            title = title,
            content = content,
            caringTypeCodeList = caringTypeList,
            taskTypeCode = taskType,
            startDate = startDate?.let { DateTimeUtils.getTimestampByLocalDate(it) },
            endDate = endDate?.let { DateTimeUtils.getTimestampByLocalDate(it) },
            days = days.map { it.type },
            startTime = startTime?.let { DateTimeUtils.getLocalTimeText(it) },
            endTime = endTime?.let { DateTimeUtils.getLocalTimeText(it) },
            emd = emd,
            latitude = latitude,
            longitude = longitude,
            salaryTypeCode = salaryType,
            salary = salary,
            indOtherConditionIdList = etcCheckedList.map { it.id },
            imageIdList = imageIdList
        )
        Log.e("request",request.toString())
        caringService.craeteJobOffer(request).suspendOnSuccess {
            emit(data)
        }.onError { Log.e("error",message())  }.onException { Log.e("error",message()) }
    }.flowOn(Dispatchers.IO)


    private suspend fun fetchImageId(imagePart: MultipartBody.Part): Int? =
        withContext(Dispatchers.IO) {
            commonService.fetchImageResponse(image = imagePart).getOrNull()?.imageId
        }


}