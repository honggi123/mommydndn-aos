package com.mommydndn.app.di

import com.mommydndn.app.domain.repository.AccountRepository
import com.mommydndn.app.domain.repository.BabyItemRepository
import com.mommydndn.app.domain.repository.CareRepository
import com.mommydndn.app.domain.repository.CommonRepositoy
import com.mommydndn.app.domain.repository.LocationRepository
import com.mommydndn.app.domain.repository.NotificationRepository
import com.mommydndn.app.domain.repository.TermsAndConditionsRepository
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.data.repository.AccountDataRepository
import com.mommydndn.app.data.repository.BabyItemDataRepository
import com.mommydndn.app.data.repository.CareDataRepository
import com.mommydndn.app.data.repository.CommonDataRepository
import com.mommydndn.app.data.repository.LocationDataRepository
import com.mommydndn.app.data.repository.NotificationDataRepository
import com.mommydndn.app.data.repository.TermsAndConditionsDataRepository
import com.mommydndn.app.data.repository.UserDataRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.Binds

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindAccountRepository(repository: AccountDataRepository): AccountRepository

    @Binds
    @Singleton
    fun bindTermsRepository(repository: TermsAndConditionsDataRepository): TermsAndConditionsRepository

    @Binds
    @Singleton
    fun bindLocationRepository(repository: LocationDataRepository): LocationRepository

    @Binds
    @Singleton
    fun bindNoticeRepository(repository: NotificationDataRepository): NotificationRepository

    @Binds
    @Singleton
    fun bindCaringRepository(repository: CareDataRepository): CareRepository

    @Binds
    @Singleton
    fun bindCommonRepository(repository: CommonDataRepository): CommonRepositoy

    @Binds
    @Singleton
    fun bindBabyItemRepository(repository: BabyItemDataRepository): BabyItemRepository

    @Binds
    @Singleton
    fun bindUserRepository(repository: UserDataRepository): UserRepository
}