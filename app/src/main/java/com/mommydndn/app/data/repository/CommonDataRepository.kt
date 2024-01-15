package com.mommydndn.app.data.repository

import com.mommydndn.app.data.network.service.CommonService
import com.mommydndn.app.domain.model.Banner
import com.mommydndn.app.domain.repository.CommonRepositoy
import javax.inject.Inject

class CommonDataRepository @Inject constructor(
    private val commonService: CommonService
) : CommonRepositoy {

    override suspend fun getBanners(): List<Banner> {
//        return commonService.fetchBanners()
        TODO()
    }
}

