package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.api.model.response.GetBannersResponse
import com.mommydndn.app.data.network.model.response.ImageResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CommonService {

    @GET("/api/banner/home")
    suspend fun fetchBanners(): GetBannersResponse

    @Multipart
    @POST("/api/image")
    suspend fun fetchImageResponse(@Part image: MultipartBody.Part): ImageResponse
}