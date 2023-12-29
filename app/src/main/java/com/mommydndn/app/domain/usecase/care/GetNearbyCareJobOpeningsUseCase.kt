package com.mommydndn.app.domain.usecase.care

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.care.CareJobOpening
import com.mommydndn.app.domain.repository.CareRepository
import com.mommydndn.app.domain.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNearbyCareJobOpeningsUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: CareRepository,
) : FlowUseCase<GetNearbyCareJobOpeningsParams, List<CareJobOpening>>(coroutineDispatcher) {

    override fun execute(parameters: GetNearbyCareJobOpeningsParams): Flow<List<CareJobOpening>> {
        with(parameters) {
            repository.getNearbyCareJobOpenings(latitude, longitude)
        }

        TODO()
    }
}

// todo
data class GetNearbyCareJobOpeningsParams(
    val latitude: Double,
    val longitude: Double,
)