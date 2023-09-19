package com.mommydndn.app.di

import com.mommydndn.app.data.api.ApiService
import com.mommydndn.app.data.api.GoogleApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return ApiService.create()
    }

    @Singleton
    @Provides
    fun provideGoogleApiService(): GoogleApiService {
        return GoogleApiService.create()
    }

}