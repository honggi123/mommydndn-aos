package com.mommydndn.app.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mommydndn.app.BuildConfig
import com.mommydndn.app.data.network.interceptor.AuthenticationInterceptor
import com.mommydndn.app.data.network.service.AuthenticationService
import com.mommydndn.app.data.network.service.BannerService
import com.mommydndn.app.data.network.service.CareService
import com.mommydndn.app.data.network.service.CommonService
import com.mommydndn.app.data.network.service.GoogleService
import com.mommydndn.app.data.network.service.ImageService
import com.mommydndn.app.data.network.service.NotificationService
import com.mommydndn.app.data.network.service.TermsService
import com.mommydndn.app.data.network.service.UserService
import com.mommydndn.app.data.preferences.PreferencesStorage
import com.mommydndn.app.domain.model.Banner
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideAuthenticationInterceptor(preferencesStorage: PreferencesStorage): Interceptor {
        return AuthenticationInterceptor(preferencesStorage)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .apply {
                if (BuildConfig.DEBUG) {
                    val loggingInterceptor = HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }

                    addInterceptor(loggingInterceptor)
                }
            }
            .build()
    }

    @Provides
    @Singleton
    fun providerConverterFactory(): Converter.Factory {
        return Json { ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType())
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthenticationService(retrofit: Retrofit): AuthenticationService {
        return retrofit.create(AuthenticationService::class.java)
    }

    @Provides
    @Singleton
    fun provideCareService(retrofit: Retrofit): CareService {
        return retrofit.create(CareService::class.java)
    }

    @Singleton
    @Provides
    fun provideNoticeService(retrofit: Retrofit): NotificationService {
        return retrofit.create(NotificationService::class.java)
    }

    @Singleton
    @Provides
    fun provideTermsService(retrofit: Retrofit): TermsService {
        return retrofit.create(TermsService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Singleton
    @Provides
    fun provideBannerService(retrofit: Retrofit): BannerService {
        return retrofit.create(BannerService::class.java)
    }

    @Singleton
    @Provides
    fun provideNotificationService(retrofit: Retrofit): NotificationService {
        return retrofit.create(NotificationService::class.java)
    }

    @Singleton
    @Provides
    fun provideImageService(retrofit: Retrofit): ImageService {
        return retrofit.create(ImageService::class.java)
    }

    @Provides
    @Singleton
    fun provideGoogleService(client: OkHttpClient, converterFactory: Converter.Factory): GoogleService {
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com")
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
            .create(GoogleService::class.java)
    }
}