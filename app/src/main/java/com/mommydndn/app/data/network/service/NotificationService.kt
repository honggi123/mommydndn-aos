package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.response.GetNotificationSettingListResponse
import retrofit2.http.GET

interface NotificationService {

    @GET("/api/notice/setting")
    suspend fun fetchUserNotificationSettings(): GetNotificationSettingListResponse
}