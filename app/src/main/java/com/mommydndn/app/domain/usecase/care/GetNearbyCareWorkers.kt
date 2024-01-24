package com.mommydndn.app.domain.usecase.care

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.repository.CareWorkerRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNearbyCareWorkers @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: CareWorkerRepository,
) {

}