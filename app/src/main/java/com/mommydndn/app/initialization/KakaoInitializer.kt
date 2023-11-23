package com.mommydndn.app.initialization

import android.content.Context
import androidx.startup.Initializer
import com.kakao.sdk.common.KakaoSdk
import com.mommydndn.app.BuildConfig

class KakaoInitializer : Initializer<Unit> {

    override fun create(context: Context) = KakaoSdk.init(context, BuildConfig.KAKAO_APP_KEY)

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}