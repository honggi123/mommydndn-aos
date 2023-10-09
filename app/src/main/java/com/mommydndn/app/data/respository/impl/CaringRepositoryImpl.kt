package com.mommydndn.app.data.respository.impl

import com.mommydndn.app.data.api.service.CaringService
import com.mommydndn.app.data.model.JobOffer
import com.mommydndn.app.data.model.JobSeeker
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
    override fun fetchNearestJobSeeker(
        onComplete: () -> Unit,
        onError: (message: String?) -> Unit
    ): Flow<List<JobSeeker>> = flow {
        caringService.fetchNearestJobSeeker().suspendOnSuccess {
            emit(data)
        }.onError {
            onError("code: $statusCode, errorBody: $errorBody")
        }.onException {
            onError(message)
        }
    }.onCompletion {
        onComplete()
    }.flowOn(Dispatchers.IO)

    override fun fetchNearestJobOffer(
        onComplete: () -> Unit,
        onError: (message: String?) -> Unit
    ): Flow<List<JobOffer>> = flow {
        caringService.fetchNearestJobOffer().suspendOnSuccess {
            val list = data.map {
                JobOffer(
                    title = it.title,
                    neighborhood = it.neighborhood,
                    salary = it.salary.toString(),
                    salaryType = it.salaryTypeCode,
                    caringType = it.caringTypeCode
                )
            }
            emit(list)
        }.onError {
            onError("code: $statusCode, errorBody: $errorBody")
        }.onException {
            onError(message)
        }
    }.onCompletion {
        onComplete()
    }.flowOn(Dispatchers.IO)
}