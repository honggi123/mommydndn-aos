package com.mommydndn.app.data.repository

import com.mommydndn.app.data.api.service.CommonService
import com.mommydndn.app.data.model.banner.Banner
import com.mommydndn.app.domain.repository.CommonRepositoy
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CommonDataRepository @Inject constructor(
    private val commonService: CommonService
) : CommonRepositoy {
    override fun fetchBanners(): Flow<List<Banner>> = flow {
        commonService.fetchBanners().suspendOnSuccess {
            val list = data.map {
                Banner(
                    bannerId = it.bannerId,
                    url = it.url ?: "",
                    targetUrl = it.targetUrl ?: ""
                )
            }
            emit(list)
        }
    }.flowOn(Dispatchers.IO)
}