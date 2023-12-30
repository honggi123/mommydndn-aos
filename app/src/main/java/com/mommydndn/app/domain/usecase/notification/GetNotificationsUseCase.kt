package com.mommydndn.app.domain.usecase.notification

import com.mommydndn.app.data.model.notification.Notification
import com.mommydndn.app.domain.repository.NotificationRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

class GetNotificationsUseCase constructor(
    private val repository: NotificationRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : UseCase<Unit, List<Notification>>(coroutineDispatcher) {

    override suspend fun execute(parameters: Unit): List<Notification> {
        return repository.fetchUserNotificationSettings()
    }

}