package com.mommydndn.app.data.respository

import com.mommydndn.app.data.model.Banner
import kotlinx.coroutines.flow.Flow

interface CommonRepositoy {
    fun fetchBanners(): Flow<List<Banner>>

}