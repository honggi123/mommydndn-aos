package com.mommydndn.app.data.network.service.common

import com.mommydndn.app.data.network.service.common.model.UpdateImageResponse
import com.mommydndn.app.data.network.service.common.response.GetBannerListResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CommonService {
    @GET("/api/banner/home")
    suspend fun fetchBanners(): GetBannerListResponse

    @Multipart
    @POST("/api/image")
    suspend fun updateImage(@Part image: MultipartBody.Part): UpdateImageResponse
}