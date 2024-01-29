package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.NetworkNotificationSetting
import retrofit2.http.GET

interface NotificationService {

    @GET("/api/notice/setting")
    fun getNotificationSettings(): List<NetworkNotificationSetting>
}