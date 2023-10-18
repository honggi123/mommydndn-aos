package com.mommydndn.app.data.respository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mommydndn.app.data.api.model.CompanyEtcCheckItem
import com.mommydndn.app.data.api.model.IndividualEtcCheckItem
import com.mommydndn.app.data.api.service.CaringService
import com.mommydndn.app.data.datasource.JobOfferSummaryPagingSource
import com.mommydndn.app.data.datasource.NearestByLocationPagingSource
import com.mommydndn.app.data.model.CaringType
import com.mommydndn.app.data.model.EtcCheckItem
import com.mommydndn.app.data.model.JobOffer
import com.mommydndn.app.data.model.JobOfferSummary
import com.mommydndn.app.data.model.JobSeeker
import com.mommydndn.app.data.model.UserType
import com.mommydndn.app.data.respository.CaringRepository
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
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

    override fun fetchEtcCheckList(userType: UserType): Flow<List<EtcCheckItem>> = flow {
        when(userType){
            UserType.INDIVIDUAL -> {
                caringService.fetchIndividualEtcCheckList().suspendOnSuccess {
                    val list= data.map {
                        EtcCheckItem(
                            displayName = it.displayName,
                            id = it.indOtherConditionId,
                            conditionCode = it.indOtherConditionCode
                        )
                    }
                    emit(list)
                }
            }
            UserType.COMPANY -> {
                caringService.fetchCompanyEtcCheckList().suspendOnSuccess {
                    val list= data.map {
                        EtcCheckItem(
                            displayName = it.displayName,
                            id = it.comOtherConditionId,
                            conditionCode = it.comOtherConditionCode
                        )
                    }
                    emit(list)
                }
            }
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

}