package com.mommydndn.app.data.respository.impl

import android.util.Log
import com.mommydndn.app.data.api.service.CaringService
import com.mommydndn.app.data.api.service.TermsService
import com.mommydndn.app.data.model.JobOffer
import com.mommydndn.app.data.model.NearestJobSeeker
import com.mommydndn.app.data.model.NoticeSetting
import com.mommydndn.app.data.model.SitterProfile
import com.mommydndn.app.data.respository.CaringRepository
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CaringRepositoryImpl @Inject constructor(
    private val caringService: CaringService
) : CaringRepository {
    override fun fetchNearestJobSeeker(): Flow<List<SitterProfile>> = flow {
        caringService.fetchNearestJobSeeker().suspendOnSuccess {
            val list = data.map {
                SitterProfile(
                    ageAndGender = it.ageAndGender,
                    caringType = it.caringType,
                    profileImgUrl = it.profileUrl,
                    name = ""
                )
            }
            emit(list)
        }.onError {}
    }

    override fun fetchNearestJobOffer(): Flow<List<JobOffer>> = flow {
        caringService.fetchNearestJobOffer().suspendOnSuccess {
            val list = data.map {
                JobOffer(
                    title = it.title,
                    neighborhood = it.neighborhood,
                    salary = it.salary.toString(),
                    salaryTypeCode = it.salaryTypeCode,
                    caringTypeCode = it.caringTypeCode
                )
            }
            emit(list)
        }.onError {}
    }
}