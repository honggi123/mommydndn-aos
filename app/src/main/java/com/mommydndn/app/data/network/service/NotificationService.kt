package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.response.GetNotificationSettingResponse
import com.mommydndn.app.data.api.model.response.GetNotificationSettingsResponse
import retrofit2.http.GET

interface NotificationService {

    @GET("/api/notice/setting")
    suspend fun fetchUserNotificationSettings(): GetNotificationSettingsResponse
}