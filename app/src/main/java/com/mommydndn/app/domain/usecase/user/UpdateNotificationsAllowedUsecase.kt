package com.mommydndn.app.domain.usecase.user

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.Notification
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

typealias Notifications = List<Notification>

@Singleton
class UpdateNotificationsAllowedUsecase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: UserRepository,
) : UseCase<Notifications, Unit>(coroutineDispatcher) {

    override suspend fun execute(notifications: Notifications) {
        repository.updateNotificationsAllowed(notifications)
    }
}

