package com.mommydndn.app.di

import android.content.Context
import com.mommydndn.app.data.datasource.TokenManager
import com.mommydndn.app.data.respository.AccountRepository
import com.mommydndn.app.data.respository.CaringRepository
import com.mommydndn.app.data.respository.CommonRepositoy
import com.mommydndn.app.data.respository.LocationRepository
import com.mommydndn.app.data.respository.NoticeRepository
import com.mommydndn.app.data.respository.TermsRepository
import com.mommydndn.app.data.respository.impl.AccountRepositoryImpl
import com.mommydndn.app.data.respository.impl.CaringRepositoryImpl
import com.mommydndn.app.data.respository.impl.CommonRepositoryImpl
import com.mommydndn.app.data.respository.impl.LocationRepositoryImpl
import com.mommydndn.app.data.respository.impl.NoticeRespositoryIml
import com.mommydndn.app.data.respository.impl.TermsRepositoryImpl
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.Binds
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindAccountRepository(
        accountRepository: AccountRepositoryImpl
    ) : AccountRepository

    @Binds
    @Singleton
    fun bindTermsRepository(
        termsRepository: TermsRepositoryImpl
    ) : TermsRepository

    @Binds
    @Singleton
    fun bindLocationRepository(
        locationRepository: LocationRepositoryImpl
    ) : LocationRepository

    @Binds
    @Singleton
    fun bindNoticeRepository(
        noticeRepository: NoticeRespositoryIml
    ) : NoticeRepository

    @Binds
    @Singleton
    fun bindCaringRepository(
        caringRepository: CaringRepositoryImpl
    ) : CaringRepository

    @Binds
    @Singleton
    fun bindCommonRepository(
        commonRepositoryImpl: CommonRepositoryImpl
    ) : CommonRepositoy
}