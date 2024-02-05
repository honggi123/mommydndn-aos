package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.NetworkNotificationSetting
import com.mommydndn.app.data.network.service.request.UpdateNotificationsAllowedRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface NotificationService {

    @GET("/api/notice/setting")
    fun getNotificationSettings(): List<NetworkNotificationSetting>


    @PUT("/api/notice/setting")
    suspend fun updateNotificationsAllowed(@Body request: UpdateNotificationsAllowedRequest)
}