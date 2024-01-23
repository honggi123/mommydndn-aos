package com.mommydndn.app.data.repository

import com.mommydndn.app.data.network.service.CommonService
import com.mommydndn.app.domain.model.Banner
import com.mommydndn.app.domain.model.Image
import com.mommydndn.app.domain.repository.CommonRepositoy
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class CommonDataRepository @Inject constructor(
    private val commonService: CommonService
) : CommonRepositoy {

    override suspend fun getBanners(): List<Banner> {
        TODO()
    }

    override suspend fun uploadFiles(files: List<File>): List<Image> {
        TODO("Not yet implemented")
    }

    suspend fun uploadImage(fileName: String) {
        File(fileName)
            .asRequestBody("image/*".toMediaType())
            .let { request -> MultipartBody.Part.create(request) }
            .let { commonService.uploadImage(it) }
    }
}

