package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.response.NoticeSettingResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface NoticeService {

    @GET("/api/notice/setting")
    suspend fun fetchUserNoticeSettings(): List<NoticeSettingResponse>
}