package com.mommydndn.app.di

import android.content.SharedPreferences
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mommydndn.app.BuildConfig
import com.mommydndn.app.data.api.interceptor.AuthenticationInterceptor
import com.mommydndn.app.data.api.service.AuthenticationService
import com.mommydndn.app.data.api.service.BabyItemService
import com.mommydndn.app.data.api.service.CareService
import com.mommydndn.app.data.api.service.CommonService
import com.mommydndn.app.data.api.service.GoogleApiService
import com.mommydndn.app.data.api.service.KakaoApiService
import com.mommydndn.app.data.api.service.MapService
import com.mommydndn.app.data.api.service.NotificationService
import com.mommydndn.app.data.api.service.TermsAndConditionsService
import com.mommydndn.app.data.api.service.UserService
import com.mommydndn.app.data.preferences.TokenManager
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
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
    fun provideAuthService(retrofit: Retrofit): AuthenticationService {
        return retrofit.create(AuthenticationService::class.java)
    }

    @Singleton
    @Provides
    fun provideMapService(retrofit: Retrofit): MapService {
        return retrofit.create(MapService::class.java)
    }

    @Singleton
    @Provides
    fun provideTermsService(retrofit: Retrofit): TermsAndConditionsService {
        return retrofit.create(TermsAndConditionsService::class.java)
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

    @Singleton
    @Provides
    fun provideCaringService(retrofit: Retrofit): CareService {
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
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
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
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
            .create(KakaoApiService::class.java)
    }

}