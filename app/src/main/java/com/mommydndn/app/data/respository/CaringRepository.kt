package com.mommydndn.app.data.respository

import com.mommydndn.app.data.model.JobOffer
import com.mommydndn.app.data.model.JobSeeker
import kotlinx.coroutines.flow.Flow

interface CaringRepository {
    fun fetchNearestJobSeeker(): Flow<List<JobSeeker>>

    fun fetchNearestJobOffer(): Flow<List<JobOffer>>

}