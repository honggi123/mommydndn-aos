package com.mommydndn.app.data.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class TokenManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    private fun putString(key: String, data: String?) {
        editor.putString(key, data)
        editor.apply()
    }

    fun putAccessToken(accessToken: String?) {
        putString(ACCESS_TOKEN, accessToken)
    }

    fun getAccessToken(): String? {
        return sharedPreferences.getString(ACCESS_TOKEN, null)
    }

    fun getRefreshToken(): String? {
        return sharedPreferences.getString(REFRESH_TOKEN, null)
    }

    fun putRefreshToken(refreshToken: String?) {
        putString(REFRESH_TOKEN, refreshToken)
    }

    companion object {
        private const val ACCESS_TOKEN = "access_token"
        private const val REFRESH_TOKEN = "refresh_token"
    }
}
