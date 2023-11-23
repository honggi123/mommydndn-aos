package com.mommydndn.app.initialization

import android.content.Context
import androidx.startup.Initializer
import com.mommydndn.app.BuildConfig
import com.navercorp.nid.NaverIdLoginSDK

class NaverInitializer : Initializer<Unit> {

    override fun create(context: Context) = NaverIdLoginSDK.initialize(
        context,
        BuildConfig.NAVER_CLIENT_ID,
        BuildConfig.NAVER_CLIENT_SECRET,
        BuildConfig.NAVER_CLIENT_NAME,
    )

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}