package com.mommydndn.app.domain.usecase.care

import com.mommydndn.app.domain.model.care.JobOffer
import com.mommydndn.app.domain.repository.CareRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNearestJobOffersUseCase @Inject constructor(
    private val repository: CareRepository,
) : UseCase<Unit, List<JobOffer>>(Dispatchers.IO) {

    override suspend fun execute(parameters: Unit): List<JobOffer> {
        return repository.fetchNearestJobOffers()
    }

}