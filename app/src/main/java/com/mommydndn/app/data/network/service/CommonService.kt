package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.api.model.response.GetBannerListResponse
import com.mommydndn.app.data.network.model.common.GetImageIdResponse
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
    suspend fun fetchImageId(@Part image: MultipartBody.Part): GetImageIdResponse
}