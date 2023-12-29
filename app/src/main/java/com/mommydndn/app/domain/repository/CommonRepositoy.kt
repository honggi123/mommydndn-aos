package com.mommydndn.app.domain.repository

import com.mommydndn.app.domain.model.banner.Banner

interface CommonRepositoy {

    suspend fun fetchBanners(): List<Banner>

}