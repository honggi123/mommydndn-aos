package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.NearestResponse
import com.mommydndn.app.data.api.model.NoticeSettingResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NoticeService {
    @GET("/api/notice/setting")
    suspend fun fetchUserNoticeSettings(): ApiResponse<List<NoticeSettingResponse>>
}