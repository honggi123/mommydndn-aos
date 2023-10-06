package com.mommydndn.app.di

import com.mommydndn.app.BuildConfig
import com.mommydndn.app.data.api.CustomConverterFactory
import com.mommydndn.app.data.api.service.AuthService
import com.mommydndn.app.data.api.service.GoogleApiService
import com.mommydndn.app.data.api.service.MapService
import com.mommydndn.app.data.api.service.TermsService
import com.mommydndn.app.data.api.TokenInterceptor
import com.mommydndn.app.data.api.service.BabyItemService
import com.mommydndn.app.data.api.service.CaringService
import com.mommydndn.app.data.api.service.CommonService
import com.mommydndn.app.data.api.service.NoticeService
import com.mommydndn.app.data.datasource.TokenManager
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Qualifier annotation class DefaultOkhttpClient
@Qualifier annotation class TokenOkhttpClient
@Qualifier annotation class DefaultRetrofit
@Qualifier annotation class TokenRetrofit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    @DefaultOkhttpClient
    fun provideDefaultOkHttpClient(): OkHttpClient {
        val logger =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    @Provides
    @Singleton
    @TokenOkhttpClient
    fun provideTokenOkHttpClient(tokenManager: TokenManager): OkHttpClient {
        val logger =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor(TokenInterceptor(tokenManager = tokenManager))
            .build()
    }

    @Provides
    @Singleton
    @TokenRetrofit
    fun provideTokenRetrofit(@TokenOkhttpClient okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(CustomConverterFactory())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    @DefaultRetrofit
    fun provideDefaultRetrofit(@DefaultOkhttpClient okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun provideAuthService(@DefaultRetrofit retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Singleton
    @Provides
    fun provideMapService(@DefaultRetrofit retrofit: Retrofit): MapService {
        return retrofit.create(MapService::class.java)
    }

    @Singleton
    @Provides
    fun provideTermsService(@TokenRetrofit retrofit: Retrofit): TermsService {
        return retrofit.create(TermsService::class.java)
    }

    @Singleton
    @Provides
    fun provideNoticeService(@TokenRetrofit retrofit: Retrofit): NoticeService {
        return retrofit.create(NoticeService::class.java)
    }

    @Singleton
    @Provides
    fun provideBabyItemService(@TokenRetrofit retrofit: Retrofit): BabyItemService {
        return retrofit.create(BabyItemService::class.java)
    }


    @Singleton
    @Provides
    fun provideCaringService(@TokenRetrofit retrofit: Retrofit): CaringService {
        return retrofit.create(CaringService::class.java)
    }

    @Singleton
    @Provides
    fun provideCommonService(@TokenRetrofit retrofit: Retrofit): CommonService {
        return retrofit.create(CommonService::class.java)
    }

    @Provides
    @Singleton
    fun provideGoogleApiService(@DefaultOkhttpClient okHttpClient: OkHttpClient): GoogleApiService {
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
            .create(GoogleApiService::class.java)
    }


}