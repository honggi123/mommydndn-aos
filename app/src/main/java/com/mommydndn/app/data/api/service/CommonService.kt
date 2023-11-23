package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.response.BannerResponse
import com.mommydndn.app.data.api.model.response.ImageResponse
import com.skydoves.sandwich.ApiResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CommonService {

    @GET("/api/banner/home")
    suspend fun fetchBanners(): ApiResponse<List<BannerResponse>>

    @Multipart
    @POST("/api/image")
    suspend fun fetchImageResponse(@Part image: MultipartBody.Part): ApiResponse<ImageResponse>
}