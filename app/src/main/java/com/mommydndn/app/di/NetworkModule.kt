package com.mommydndn.app.di

import android.content.SharedPreferences
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mommydndn.app.BuildConfig
import com.mommydndn.app.data.network.service.notification.NotificationService
import com.mommydndn.app.data.network.interceptor.AuthenticationInterceptor
import com.mommydndn.app.data.network.service.auth.AuthService
import com.mommydndn.app.data.network.service.babyitem.BabyItemService
import com.mommydndn.app.data.network.service.care.CareService
import com.mommydndn.app.data.network.service.common.CommonService
import com.mommydndn.app.data.network.service.google.GoogleApiService
import com.mommydndn.app.data.network.service.kakao.KakaoApiService
import com.mommydndn.app.data.network.service.tos.TosService
import com.mommydndn.app.data.network.service.user.UserService
import com.mommydndn.app.data.preferences.TokenManager
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
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideTokenManager(sharedPreferences: SharedPreferences): TokenManager {
        return TokenManager(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideAuthenticationInterceptor(tokenManager: TokenManager): Interceptor {
        return AuthenticationInterceptor(tokenManager)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .apply {
                if (BuildConfig.DEBUG) {
                    val loggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

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
    fun provideAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    /*
    @Singleton
    @Provides
    fun provideMapService(retrofit: Retrofit): MapService {
        return retrofit.create(MapService::class.java)
    }
     */

    @Singleton
    @Provides
    fun provideTermsService(retrofit: Retrofit): TosService {
        return retrofit.create(TosService::class.java)
    }

    @Singleton
    @Provides
    fun provideNoticeService(retrofit: Retrofit): NotificationService {
        return retrofit.create(NotificationService::class.java)
    }

    @Singleton
    @Provides
    fun provideBabyItemService(retrofit: Retrofit): BabyItemService {
        return retrofit.create(BabyItemService::class.java)
    }

    /*
    @Singleton
    @Provides
    fun provideCaringService(retrofit: Retrofit): CareService {
        return retrofit.create(CareService::class.java)
    }
     */

    @Provides
    @Singleton
    fun provideCareService(retrofit: Retrofit): CareService {
        return retrofit.create(CareService::class.java)
    }

    @Singleton
    @Provides
    fun provideCommonService(retrofit: Retrofit): CommonService {
        return retrofit.create(CommonService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideGoogleApiService(client: OkHttpClient): GoogleApiService {
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GoogleApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideKakaoApiService(client: OkHttpClient): KakaoApiService {
        return Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com/")
            .client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(KakaoApiService::class.java)
    }

}