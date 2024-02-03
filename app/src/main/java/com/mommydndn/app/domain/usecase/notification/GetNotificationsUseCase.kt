package com.mommydndn.app.domain.usecase.notification

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.Notification
import com.mommydndn.app.domain.repository.NotificationRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNotificationsUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: NotificationRepository,
) : UseCase<Unit, List<Notification>>(coroutineDispatcher) {

    override suspend fun execute(parameters: Unit): List<Notification> {
        return repository.getNotifications()
    }
}