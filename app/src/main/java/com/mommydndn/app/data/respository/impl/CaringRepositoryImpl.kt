package com.mommydndn.app.data.respository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mommydndn.app.data.api.service.CaringService
import com.mommydndn.app.data.datasource.pagingsource.JobOfferSummaryPagingSource
import com.mommydndn.app.data.model.care.CaringTypeItem
import com.mommydndn.app.data.model.care.EtcCheckItem
import com.mommydndn.app.data.model.care.JobOffer
import com.mommydndn.app.data.model.care.JobOfferSummary
import com.mommydndn.app.data.model.care.JobSeeker
import com.mommydndn.app.data.model.care.MinHourlySalary
import com.mommydndn.app.data.model.user.UserType
import com.mommydndn.app.data.respository.CaringRepository
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CaringRepositoryImpl @Inject constructor(
    private val caringService: CaringService
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
}