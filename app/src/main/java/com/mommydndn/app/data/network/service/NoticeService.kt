package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.response.NoticeSettingResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface NoticeService {

    @GET("/api/notice/setting")
    suspend fun fetchUserNoticeSettings(): ApiResponse<List<NoticeSettingResponse>>
}