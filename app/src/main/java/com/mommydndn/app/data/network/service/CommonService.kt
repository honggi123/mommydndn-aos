package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.api.model.response.GetBannerListResponse
import com.mommydndn.app.data.network.model.Image.UpdateImageResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CommonService {

    @GET("/api/banner/home")
    suspend fun getBanners(): GetBannerListResponse

    @Multipart
    @POST("/api/image")
    suspend fun updateImage(@Part image: MultipartBody.Part): UpdateImageResponse
}