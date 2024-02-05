package com.mommydndn.app.data.repository

import com.mommydndn.app.data.mapper.mapToDomainBanners
import com.mommydndn.app.data.network.service.CommonService
import com.mommydndn.app.domain.model.Banner
import com.mommydndn.app.domain.model.Image
import com.mommydndn.app.domain.repository.BannerRepository
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class BannerDataRepository @Inject constructor(
    private val commonService: CommonService
) : BannerRepository {

    override suspend fun getBanners(): List<Banner> {
       return commonService.getBanners()
           .mapToDomainBanners()
    }
}

