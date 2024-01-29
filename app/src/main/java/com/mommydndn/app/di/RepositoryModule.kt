package com.mommydndn.app.di

import com.mommydndn.app.data.repository.CaregiverJobPostingDataRepository
import com.mommydndn.app.data.repository.CommonDataRepository
import com.mommydndn.app.data.repository.LocationDataRepository
import com.mommydndn.app.data.repository.TermsOfServiceDataRepository
import com.mommydndn.app.data.repository.UserDataRepository
import com.mommydndn.app.domain.repository.CaregiverJobPostingRepository
import com.mommydndn.app.domain.repository.CommonRepositoy
import com.mommydndn.app.domain.repository.LocationRepository
import com.mommydndn.app.domain.repository.TermsOfServiceRepository
import com.mommydndn.app.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindLocationRepository(repository: LocationDataRepository): LocationRepository

    @Binds
    @Singleton
    fun bindCareJobRepository(repository: CaregiverJobPostingDataRepository): CaregiverJobPostingRepository

    @Binds
    @Singleton
    fun bindCommonRepository(repository: CommonDataRepository): CommonRepositoy

    @Binds
    @Singleton
    fun bindUserRepository(repository: UserDataRepository): UserRepository

    @Binds
    @Singleton
    fun bindTermsOfServiceRepository(repository: TermsOfServiceDataRepository): TermsOfServiceRepository
}
