package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.BannerResponse
import com.mommydndn.app.data.model.Banner
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface CommonService {
    @GET("/api/banner/home")
    suspend fun fetchBanners(): ApiResponse<List<BannerResponse>>
}