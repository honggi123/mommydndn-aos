package com.mommydndn.app.domain.repository

import com.mommydndn.app.domain.model.Banner

interface BannerRepository {

    suspend fun getBanners(): List<Banner>
}