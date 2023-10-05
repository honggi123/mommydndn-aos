package com.mommydndn.app.data.respository.impl

import com.mommydndn.app.data.api.service.CommonService
import com.mommydndn.app.data.model.Banner
import com.mommydndn.app.data.model.SitterProfile
import com.mommydndn.app.data.respository.CommonRepositoy
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CommonRepositoryImpl @Inject constructor(
    private val commonService: CommonService
) : CommonRepositoy {
    override fun fetchBanners(): Flow<List<Banner>> = flow {
        commonService.fetchBanners().suspendOnSuccess {
            val list = data.map {
                Banner(
                    imgUrl = it.url ?: "",
                    targetUrl = it.targetUrl ?: ""
                )
            }
            emit(list)
        }.onError {}
    }
}