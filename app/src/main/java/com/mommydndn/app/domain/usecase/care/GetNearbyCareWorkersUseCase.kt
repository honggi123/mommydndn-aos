package com.mommydndn.app.domain.usecase.care

import com.mommydndn.app.domain.repository.CareRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNearbyCareWorkersUseCase @Inject constructor(
    private val repository: CareRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

}