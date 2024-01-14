package com.mommydndn.app.data.repository

import androidx.paging.PagingData
import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.data.model.care.CaringTypeItem
import com.mommydndn.app.data.model.care.EtcCheckItem
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.care.SortingType
import com.mommydndn.app.data.model.care.WorkPeriodType
import com.mommydndn.app.data.model.common.DayOfWeekItem
import com.mommydndn.app.data.model.common.DayOfWeekType
import com.mommydndn.app.data.network.service.care.CareService
import com.mommydndn.app.data.network.service.care.response.AgencyCareWorkerSummaryApiModel
import com.mommydndn.app.data.network.service.care.response.CareWorkerSummaryApiModel
import com.mommydndn.app.data.network.service.care.response.GetMinimumWageResponse
import com.mommydndn.app.data.network.service.care.response.CareJobSummaryApiModel
import com.mommydndn.app.data.network.service.common.CommonService
import com.mommydndn.app.domain.model.care.JobOffer
import com.mommydndn.app.domain.model.care.JobSeeker
import com.mommydndn.app.domain.repository.CareRepository
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

    override fun fetchJobOffer(jobOfferId: Int): Flow<JobOffer> {
//       return careService.fetchCareJob(jobOfferId)
        TODO()
    }

    override suspend fun fetchNearestJobSeekers(): List<JobSeeker> {
//       return careService.fetchNearestCareWorkerList()
        TODO()
    }

    override suspend fun fetchNearestJobOffers(): List<JobOffer> {
//       return careService.fetchNearestCareJobList().       toDomain()
        TODO()
    }

    override fun fetchEtcIndividualCheckList(): Flow<List<EtcCheckItem>> {
//      return careService.fetchOtherIndividualConditions()
        TODO()
    }

    override fun fetchCompanyEtcCheckList(): Flow<List<EtcCheckItem>> {
//       return careService.fetchOtherAgencyCondtions()
        TODO()
    }

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
    ): Flow<PagingData<CareJobSummaryApiModel>> {

//        val workPeriodTypeList = if (workPeriodType == null) {
//            listOf(WorkPeriodType.ONETIME, WorkPeriodType.REGULAR)
//        } else {
//            listOf(workPeriodType)
//        }
//
//        val getCareJobListRequest =
//            com.mommydndn.app.data.network.service.care.request.GetCareJobListRequest(
//                caringTypeCodeList = caringTypeList,
//                days = days,
//                emdId = emdId,
//                endTime = endTime?.let { DateTimeUtils.getLocalTimeText(it) } ?: null,
//                keyword = keyword,
//                neighborhoodScope = neighborhoodScope,
//                paginationRequest = PaginationRequest(0, 0, 0),
//                sortingCondition = sortingType,
//                startTime = startTime?.let { DateTimeUtils.getLocalTimeText(it) } ?: null,
//                taskTypeCodeList = workPeriodTypeList
//            )
//
//        return Pager(
//            config = PagingConfig(
//                pageSize = 5, enablePlaceholders = false
//            ),
//            pagingSourceFactory = {
//                CareJobSummaryPagingSource(
//                    getCareJobListRequest,
//                    careService
//                )
//            }
//        ).flow
        TODO("Not yet implemented")
    }

    override fun fetchJobSeekerSummary(
        keyword: String?,
        sortingType: SortingType,
        emdId: Int,
        neighborhoodScope: Int,
        caringTypeList: List<CaringType>
    ): Flow<PagingData<CareWorkerSummaryApiModel>> {
//        val jobsSeekerListRequest =
//            com.mommydndn.app.data.network.service.care.request.GetCareWorkerListRequest(
//                caringTypeCodeList = caringTypeList,
//                emdId = emdId,
//                keyword = keyword,
//                neighborhoodScope = neighborhoodScope,
//                paginationRequest = PaginationRequest(0, 0, 0),
//                sortingCondition = sortingType,
//            )
//
//        return Pager(
//            config = PagingConfig(
//                pageSize = 5, enablePlaceholders = false
//            ),
//            pagingSourceFactory = {
//                CareWorkerSummaryPagingSource(
//                    jobsSeekerListRequest,
//                    careService
//                )
//            }
//        ).flow
        TODO("Not yet implemented")
    }

    override fun fetchCompanySummary(
        keyword: String?,
        sortingType: SortingType,
        emdId: Int,
        neighborhoodScope: Int,
        caringTypeList: List<CaringType>
    ): Flow<PagingData<AgencyCareWorkerSummaryApiModel>> {
//        val companyListRequest =
//            com.mommydndn.app.data.network.service.care.request.GetAgencyCareWorkerListRequest(
//                caringTypeCodeList = caringTypeList,
//                emdId = emdId,
//                keyword = keyword,
//                neighborhoodScope = neighborhoodScope,
//                paginationRequest = PaginationRequest(0, 0, 0),
//                sortingCondition = sortingType,
//                minMonthlySalary = null,
//                maxMonthlySalary = null
//            )
//
//        return Pager(
//            config = PagingConfig(
//                pageSize = 5, enablePlaceholders = false
//            ),
//            pagingSourceFactory = {
//                AgencyCareWorkerSummaryPagingSource(
//                    companyListRequest,
//                    careService
//                )
//            }
//        ).flow
        TODO("Not yet implemented")
    }

    override fun fetchCaringTypeItems(): Flow<List<CaringTypeItem>> = flow<List<CaringTypeItem>> {
//        careService.fetchCaringTypesResponse()
        TODO("Not yet implemented")
    }.flowOn(Dispatchers.IO)

    override fun fetchMinHourlySalary(): Flow<GetMinimumWageResponse> {
//        flow<GetMinHourlySalaryResponse> {
//            careService.fetchMinHourlySalary()
//        }.flowOn(Dispatchers.IO)
        TODO()
    }

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
        latitude: Double,
        longitude: Double,
        salaryType: SalaryType,
        salary: Int,
        etcCheckedList: List<EtcCheckItem>,
        imageList: List<MultipartBody.Part>
    ): Flow<com.mommydndn.app.data.network.service.care.response.CreateCareJobResponse> {
        TODO("Not yet implemented")
    }

    override fun createJobSeeker(
        introduce: String,
        caringTypeList: List<CaringType>,
        latitude: Double?,
        longitude: Double?,
        salaryType: SalaryType,
        salary: Int,
        etcCheckedList: List<EtcCheckItem>
    ): Flow<com.mommydndn.app.data.network.service.care.response.CreateCareWorkerResponse> {
        TODO("Not yet implemented")
    }

    override fun createCompany(
        introduce: String,
        coverImageList: List<MultipartBody.Part>,
        caringTypeList: List<CaringType>,
        latitude: Double?,
        longitude: Double?,
        minSalary: Int,
        maxSalary: Int,
        etcCheckedList: List<EtcCheckItem>,
        commission: Int
    ): Flow<com.mommydndn.app.data.network.service.care.response.CreateAgencyCareWorkerResponse> {
        TODO("Not yet implemented")
    }

    /*
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
     */

    private suspend fun fetchImageId(imagePart: MultipartBody.Part): Int =
        withContext(Dispatchers.IO) {
            commonService.updateImage(image = imagePart).id
        }
}
/*
@Singleton
class CareDataRepository @Inject constructor(
private val careService: CareService
) : CareRepository {

override fun getNearbyCareCareJobs(
    latitude: Double,
    longitude: Double
): Flow<PagingData<CareCareJob>> {
    val page = 1

    val pageSize = 20

    val request = GetCareCareJobListRequest(
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
            CareJobListPagingSource(
                request = request,
                careService = careService
            )
        }
    ).flow
}
}
 */