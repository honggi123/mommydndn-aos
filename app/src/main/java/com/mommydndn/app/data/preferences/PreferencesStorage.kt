package com.mommydndn.app.data.preferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

// TODO
@Singleton
class PreferencesStorage @Inject constructor(context: Context) {

    companion object {
        private const val NAME = "mommydndn"
        private const val ACCESS_TOKEN = "access_token"
        private const val REFRESH_TOKEN = "refresh_token"
    }

    private val sharedPreferences = context.getSharedPreferences(NAME, MODE_PRIVATE)

    fun setAccessToken(accessToken: String?) = sharedPreferences.edit(commit = true) {
        putString(ACCESS_TOKEN, accessToken)
    }

    fun getAccessToken(): String? = sharedPreferences.getString(ACCESS_TOKEN, null)

    fun setRefreshToken(refreshToken: String?) = sharedPreferences.edit(commit = true) {
        putString(REFRESH_TOKEN, refreshToken)
    }

    fun getRefreshToken(): String? {
        return sharedPreferences.getString(REFRESH_TOKEN, null)
    }
}
