package com.mommydndn.app.data.respository

import com.mommydndn.app.data.model.JobOffer
import com.mommydndn.app.data.model.SitterProfile
import kotlinx.coroutines.flow.Flow

interface CaringRepository {
    fun fetchNearestJobSeeker(): Flow<List<SitterProfile>>

    fun fetchNearestJobOffer(): Flow<List<JobOffer>>

}