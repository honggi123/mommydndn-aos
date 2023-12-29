package com.mommydndn.app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
<<<<<<<< HEAD:app/src/main/java/com/mommydndn/app/data/repository/CareDataRepository.kt
import com.mommydndn.app.data.api.model.request.CompanyCreationRequest
import com.mommydndn.app.data.api.model.request.CompanyListRequest
import com.mommydndn.app.data.api.model.request.JobOfferListRequest
import com.mommydndn.app.data.api.model.request.JobOfferCreationRequest
import com.mommydndn.app.data.api.model.request.JobSeekerCreationRequest
import com.mommydndn.app.data.api.model.request.JobSeekerListRequest
import com.mommydndn.app.data.api.model.request.PaginationRequest
import com.mommydndn.app.data.api.model.response.CompanyCreationResponse
import com.mommydndn.app.data.api.model.response.JobOfferCreationResponse
import com.mommydndn.app.data.api.model.response.JobOfferResponse
import com.mommydndn.app.data.api.model.response.JobSeekerCreationResponse
import com.mommydndn.app.data.api.model.response.toDomain
import com.mommydndn.app.data.api.service.CareService
import com.mommydndn.app.data.api.service.CommonService
import com.mommydndn.app.data.datasource.pagingsource.CompanySummaryPagingSource
import com.mommydndn.app.data.datasource.pagingsource.JobOfferSummaryPagingSource
import com.mommydndn.app.data.datasource.pagingsource.JobSeekerSummaryPagingSource
========
import com.mommydndn.app.data.network.model.request.CompanyCreationRequest
import com.mommydndn.app.data.network.model.request.CompanyListRequest
import com.mommydndn.app.data.network.model.request.JobOfferListRequest
import com.mommydndn.app.data.network.model.request.JobOfferCreationRequest
import com.mommydndn.app.data.network.model.request.JobSeekerCreationRequest
import com.mommydndn.app.data.network.model.request.JobSeekerListRequest
import com.mommydndn.app.data.network.model.request.PaginationRequest
import com.mommydndn.app.data.network.model.response.CompanyCreationResponse
import com.mommydndn.app.data.network.model.response.JobOfferCreationResponse
import com.mommydndn.app.data.network.model.response.JobOfferResponse
import com.mommydndn.app.data.network.model.response.JobSeekerCreationResponse
import com.mommydndn.app.data.network.service.CaringService
import com.mommydndn.app.data.network.service.CommonService
import com.mommydndn.app.data.source.pagingsource.CompanySummaryPagingSource
import com.mommydndn.app.data.source.pagingsource.JobOfferSummaryPagingSource
import com.mommydndn.app.data.source.pagingsource.JobSeekerSummaryPagingSource
>>>>>>>> refactor/code_care:app/src/main/java/com/mommydndn/app/data/repository/CaringDataRepository.kt
import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeItem
import com.mommydndn.app.data.model.care.EtcCheckItem
import com.mommydndn.app.domain.model.care.JobOffer
import com.mommydndn.app.data.model.care.summary.JobOfferSummaryListItem
import com.mommydndn.app.domain.model.care.JobSeeker
import com.mommydndn.app.data.model.care.summary.JobSeekerSummaryItem
import com.mommydndn.app.data.model.care.MinHourlySalary
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.care.SortingType
import com.mommydndn.app.data.model.care.WorkPeriodType
import com.mommydndn.app.data.model.care.summary.CompanySummaryListItem
import com.mommydndn.app.data.model.common.DayOfWeekItem
import com.mommydndn.app.data.model.common.DayOfWeekType
import com.mommydndn.app.data.model.map.EmdItem
import com.mommydndn.app.domain.repository.CareRepository
import com.mommydndn.app.util.DateTimeUtils
import com.skydoves.sandwich.getOrNull
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

class CareDataRepository @Inject constructor(
    private val careService: CareService,
    private val commonService: CommonService
) : CareRepository {

    override fun fetchJobOffer(jobOfferId: Int): Flow<JobOfferResponse> = flow<JobOfferResponse> {
        careService.fetchJobOffer(jobOfferId).suspendOnSuccess {
            emit(data)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun fetchNearestJobSeekers(): List<JobSeeker> =
        careService.fetchNearestJobSeeker().toDomain()

    override suspend fun fetchNearestJobOffers(): List<JobOffer> =
        careService.fetchNearestJobOffer().toDomain()


    override fun fetchEtcIndividualCheckList(): Flow<List<EtcCheckItem>> = flow {
        careService.fetchIndividualEtcCheckList().suspendOnSuccess {
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

    override fun fetchCompanyEtcCheckList(): Flow<List<EtcCheckItem>> = flow {
        careService.fetchCompanyEtcCheckList().suspendOnSuccess {
            val list = data.map {
                EtcCheckItem(
                    displayName = it.displayName,
                    id = it.comOtherConditionId,
                    conditionCode = it.comOtherConditionCode
                )
            }
            emit(list)
        }
    }.flowOn(Dispatchers.IO)

    override fun fetchJobOfferSummary(
        keyword: String?,
        sortingType: SortingType,
        emdId: Int,
        neighborhoodScope: Int,
        caringTypeList: List<CaringType>,
        days: List<DayOfWeekType>,
        startTime: LocalTime?,
        endTime: LocalTime?,
        workPeriodType: WorkPeriodType?
    ): Flow<PagingData<JobOfferSummaryListItem>> {

        val workPeriodTypeList = if (workPeriodType == null) {
            listOf(WorkPeriodType.ONETIME, WorkPeriodType.REGULAR)
        } else {
            listOf(workPeriodType)
        }

        val jobOfferListRequest = JobOfferListRequest(
            caringTypeCodeList = caringTypeList,
            days = days,
            emdId = emdId,
            endTime = endTime?.let { DateTimeUtils.getLocalTimeText(it) } ?: null,
            keyword = keyword,
            neighborhoodScope = neighborhoodScope,
            paginationRequest = PaginationRequest(0, 0, 0),
            sortingCondition = sortingType,
            startTime = startTime?.let { DateTimeUtils.getLocalTimeText(it) } ?: null,
            taskTypeCodeList = workPeriodTypeList
        )

        return Pager(
            config = PagingConfig(
                pageSize = 5, enablePlaceholders = false
            ),
            pagingSourceFactory = {
                JobOfferSummaryPagingSource(
                    jobOfferListRequest,
                    careService
                )
            }
        ).flow
    }

    override fun fetchJobSeekerSummary(
        keyword: String?,
        sortingType: SortingType,
        emdId: Int,
        neighborhoodScope: Int,
        caringTypeList: List<CaringType>
    ): Flow<PagingData<JobSeekerSummaryItem>> {
        val jobsSeekerListRequest = JobSeekerListRequest(
            caringTypeCodeList = caringTypeList,
            emdId = emdId,
            keyword = keyword,
            neighborhoodScope = neighborhoodScope,
            paginationRequest = PaginationRequest(0, 0, 0),
            sortingCondition = sortingType,
        )

        return Pager(
            config = PagingConfig(
                pageSize = 5, enablePlaceholders = false
            ),
            pagingSourceFactory = {
                JobSeekerSummaryPagingSource(
                    jobsSeekerListRequest,
                    careService
                )
            }
        ).flow
    }

    override fun fetchCompanySummary(
        keyword: String?,
        sortingType: SortingType,
        emdId: Int,
        neighborhoodScope: Int,
        caringTypeList: List<CaringType>
    ): Flow<PagingData<CompanySummaryListItem>> {
        val companyListRequest = CompanyListRequest(
            caringTypeCodeList = caringTypeList,
            emdId = emdId,
            keyword = keyword,
            neighborhoodScope = neighborhoodScope,
            paginationRequest = PaginationRequest(0, 0, 0),
            sortingCondition = sortingType,
            minMonthlySalary = null,
            maxMonthlySalary = null
        )

        return Pager(
            config = PagingConfig(
                pageSize = 5, enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CompanySummaryPagingSource(
                    companyListRequest,
                    careService
                )
            }
        ).flow
    }

    override fun fetchCaringTypeItems(): Flow<List<CaringTypeItem>> = flow {
        careService.fetchCaringTypesResponse().suspendOnSuccess {
            val list = data.map {
                CaringTypeItem(
                    caringType = it.caringTypeCode,
                    displayName = it.displayName,
                )
            }
            emit(list)
        }
    }.flowOn(Dispatchers.IO)

    override fun fetchMinHourlySalary(): Flow<MinHourlySalary> = flow {
        careService.fetchMinHourlySalary().suspendOnSuccess {
            emit(data)
        }
    }.flowOn(Dispatchers.IO)

    override fun createJobOffer(
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
        imageList: List<MultipartBody.Part>
    ): Flow<JobOfferCreationResponse> = flow {

        val imageIdList = imageList.map {
            val id = fetchImageId(it) ?: 0
            id
        }

        val request = if (taskType == WorkPeriodType.REGULAR) {
            JobOfferCreationRequest(
                title = title,
                content = content,
                caringTypeCodeList = caringTypeList,
                taskTypeCode = taskType,
                dateList = emptyList(),
                days = days.map { it.type },
                startTime = startTime?.let { DateTimeUtils.getLocalTimeText(it) },
                endTime = endTime?.let { DateTimeUtils.getLocalTimeText(it) },
                emd = emd,
                latitude = latitude,
                longitude = longitude,
                salaryTypeCode = salaryType,
                salary = salary,
                indOtherConditionIdList = etcCheckedList.map { it.id },
                imageIdList = imageIdList,
                startDate = DateTimeUtils.getTimestampByLocalDate(startDate),
                endDate = DateTimeUtils.getTimestampByLocalDate(endDate)
            )
        } else {
            JobOfferCreationRequest(
                title = title,
                content = content,
                caringTypeCodeList = caringTypeList,
                taskTypeCode = taskType,
                dateList = dateList!!.map { DateTimeUtils.getTimestampByLocalDate(it) ?: 0 },
                days = days.map { it.type },
                startTime = startTime?.let { DateTimeUtils.getLocalTimeText(it) },
                endTime = endTime?.let { DateTimeUtils.getLocalTimeText(it) },
                emd = emd,
                latitude = latitude,
                longitude = longitude,
                salaryTypeCode = salaryType,
                salary = salary,
                indOtherConditionIdList = etcCheckedList.map { it.id },
                imageIdList = imageIdList,
                startDate = null,
                endDate = null
            )
        }


        careService.createJobOffer(request).suspendOnSuccess {
            emit(data)
        }
    }.flowOn(Dispatchers.IO)

    override fun createJobSeeker(
        introduce: String,
        caringTypeList: List<CaringType>,
        emd: EmdItem,
        latitude: Double?,
        longitude: Double?,
        salaryType: SalaryType,
        salary: Int,
        etcCheckedList: List<EtcCheckItem>
    ): Flow<JobSeekerCreationResponse> = flow {
        val request = JobSeekerCreationRequest(
            introLine = introduce,
            caringTypeCodeList = caringTypeList,
            emd = emd,
            latitude = latitude,
            longitude = longitude,
            salaryTypeCode = salaryType,
            salary = salary,
            indOtherConditionIdList = etcCheckedList.map { it.id },
        )
        careService.createJobSeeker(request).suspendOnSuccess {
            emit(data)
        }
    }.flowOn(Dispatchers.IO)

    override fun createCompany(
        introduce: String,
        coverImageList: List<MultipartBody.Part>,
        caringTypeList: List<CaringType>,
        emd: EmdItem,
        latitude: Double?,
        longitude: Double?,
        minSalary: Int,
        maxSalary: Int,
        etcCheckedList: List<EtcCheckItem>,
        commission: Int
    ): Flow<CompanyCreationResponse> = flow {

        val coverImageIdList = coverImageList.map { fetchImageId(it) ?: 0 }

        val request = CompanyCreationRequest(
            introLine = introduce,
            caringTypeCodeList = caringTypeList,
            emd = emd,
            latitude = latitude,
            longitude = longitude,
            minMonthlySalary = minSalary,
            maxMonthlySalary = maxSalary,
            comOtherConditionIdList = etcCheckedList.map { it.id },
            commission = commission,
            coverImageIdList = coverImageIdList
        )

        careService.createCompany(request).suspendOnSuccess {
            emit(data)
        }
    }.flowOn(Dispatchers.IO)

    private suspend fun fetchImageId(imagePart: MultipartBody.Part): Int? =
        withContext(Dispatchers.IO) {
            commonService.fetchImageResponse(image = imagePart).getOrNull()?.imageId
        }


import com.mommydndn.app.data.model.care.SortingType
import com.mommydndn.app.data.network.model.care.GetCareJobOpeningListRequest
import com.mommydndn.app.data.network.model.care.PageMeta
import com.mommydndn.app.data.network.service.CareService
import com.mommydndn.app.data.source.paging.JobOpeningListPagingSource
import com.mommydndn.app.domain.model.care.CareJobOpening
import com.mommydndn.app.domain.repository.CareRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CareDataRepository @Inject constructor(
    private val careService: CareService
) : CareRepository {

    override fun getNearbyCareJobOpenings(
        latitude: Double,
        longitude: Double
    ): Flow<PagingData<CareJobOpening>> {
        val page = 1

        val pageSize = 20

        val request = GetCareJobOpeningListRequest(
            pageMeta = PageMeta(
                page = page,
                size = pageSize,
                requestedAt = System.currentTimeMillis(),
            ),
            keyword = null,
            neighborhoodId = -1,
            nearbyNeighborhoodDistance = -1,
            careTypes = emptyList(),
            daysOfWeek = emptyList(),
            orderBy = SortingType.LATEST,
            startTime = null,
            endTime = null,
            workPeriods = emptyList(),
        )

        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                JobOpeningListPagingSource(
                    request = request,
                    careService = careService
                )
            }
        ).flow
    }
}