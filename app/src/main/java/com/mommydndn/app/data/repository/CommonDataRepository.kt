package com.mommydndn.app.data.repository

import com.mommydndn.app.data.api.model.response.toDomain
import com.mommydndn.app.data.api.service.CommonService
import com.mommydndn.app.domain.model.banner.Banner
import com.mommydndn.app.domain.repository.CommonRepositoy
import javax.inject.Inject

class CommonDataRepository @Inject constructor(
    private val commonService: CommonService
) : CommonRepositoy {
    override suspend fun fetchBanners(): List<Banner> {
        return commonService.fetchBanners().map {
            it.toDomain()
        }
    }
}