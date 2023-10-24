package com.mommydndn.app.data.respository

import com.mommydndn.app.data.model.banner.Banner
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface CommonRepositoy {
    fun fetchBanners(): Flow<List<Banner>>
}