package com.mommydndn.app.domain.usecase.care

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.CaregiverJobApplication
import com.mommydndn.app.domain.repository.CaregiverJobApplicationRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNearbyCaregiverJobApplicationsUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: CaregiverJobApplicationRepository,
) : UseCase<Unit, List<CaregiverJobApplication>>(coroutineDispatcher) {

    override suspend fun execute(parameters: Unit): List<CaregiverJobApplication> {
        return repository.getNearbytCaregiverJobApplication()
    }
}