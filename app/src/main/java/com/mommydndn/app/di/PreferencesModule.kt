package com.mommydndn.app.di

import android.content.Context
import com.mommydndn.app.data.preferences.PreferencesStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun providePreferencesStorage(@ApplicationContext context: Context): PreferencesStorage {
        return PreferencesStorage(context)
    }
}
