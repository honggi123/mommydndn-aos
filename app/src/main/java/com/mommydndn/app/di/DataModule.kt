package com.mommydndn.app.di

import com.mommydndn.app.data.respository.AccountRepository
import com.mommydndn.app.data.respository.TermsRepository
import com.mommydndn.app.data.respository.impl.AccountRepositoryImpl
import com.mommydndn.app.data.respository.impl.TermsRepositoryImpl
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
    fun bindAccountRepository(
        accountRepository: AccountRepositoryImpl
    ) : AccountRepository

    @Binds
    @Singleton
    fun bindTermsRepository(
        termsRepository: TermsRepositoryImpl
    ) : TermsRepository
}