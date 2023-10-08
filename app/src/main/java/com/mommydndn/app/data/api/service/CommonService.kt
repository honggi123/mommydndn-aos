package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.BannerResponse
import com.mommydndn.app.data.api.model.LoginGoogleRequest
import com.mommydndn.app.data.api.model.LoginGoogleResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CommonService {
    @GET("/api/banner/home")
    suspend fun fetchBanners(): ApiResponse<List<BannerResponse>>
}