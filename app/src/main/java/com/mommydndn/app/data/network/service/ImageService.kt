package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.service.request.UpdateProfileImageRequest
import com.mommydndn.app.data.network.service.response.UploadImageResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part


interface ImageService {


    @POST("/api/image")
    @Multipart
    suspend fun uploadImage(@Part image: MultipartBody.Part): UploadImageResponse

    @PUT("/api/user/profile-image")
    suspend fun updateProfileImage(@Body request: UpdateProfileImageRequest)
}