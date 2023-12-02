package com.mommydndn.app.domain.usecase.caring

import com.mommydndn.app.data.model.care.JobOffer
import com.mommydndn.app.data.model.care.JobSeeker
import com.mommydndn.app.domain.repository.CaringRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNearestJobOffersUseCase @Inject constructor(
    private val repository: CaringRepository,
) : UseCase<Unit, List<JobOffer>>(Dispatchers.IO) {

    override suspend fun execute(parameters: Unit): List<JobOffer> {
        return repository.fetchNearestJobOffer()
    }

}