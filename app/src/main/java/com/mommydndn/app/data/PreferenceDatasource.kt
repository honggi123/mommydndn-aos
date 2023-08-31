package com.mommydndn.app.data

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Preferences @Inject constructor(
    @ApplicationContext context: Context,
) {

    private fun getPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    private val prefs = getPreference(context)
    private val editor = prefs.edit()

    companion object {
        private const val PREFERENCE_NAME = "preference"
        private const val ACCESS_TOKEN = "accesstoken"
        private const val REFRESH_TOKEN = "refreshtoken"
    }

    private fun putString(key: String, data: String?) {
        editor.putString(key, data)
        editor.apply()
    }

    private fun getString(key: String, defValue: String? = null): String? {
        return prefs.getString(key, defValue)
    }

    fun putAccessToken(accessToken: String?) {
        putString(ACCESS_TOKEN,accessToken)
    }

    fun putRefreshToken(refreshToken: String?) {
        putString(REFRESH_TOKEN,refreshToken)
    }
}
