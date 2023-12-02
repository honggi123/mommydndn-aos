package com.mommydndn.app.domain.usecase.care

import com.mommydndn.app.data.model.care.JobSeeker
import com.mommydndn.app.domain.repository.CaringRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNearestJobSeekersUseCase @Inject constructor(
    private val repository: CaringRepository,
) : UseCase<Unit, List<JobSeeker>>(Dispatchers.IO) {

    override suspend fun execute(parameters: Unit): List<JobSeeker> {
        return repository.fetchNearestJobSeeker()
    }

}