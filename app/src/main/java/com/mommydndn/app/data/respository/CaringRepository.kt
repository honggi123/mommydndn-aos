package com.mommydndn.app.data.respository

import com.mommydndn.app.data.model.JobOffer
import com.mommydndn.app.data.model.JobSeeker
import kotlinx.coroutines.flow.Flow

interface CaringRepository {
    fun fetchNearestJobSeeker(
        onComplete: () -> Unit,
        onError: (message: String?) -> Unit
    ): Flow<List<JobSeeker>>

    fun fetchNearestJobOffer(
        onComplete: () -> Unit,
        onError: (message: String?) -> Unit
    ): Flow<List<JobOffer>>

}