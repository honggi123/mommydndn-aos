package com.mommydndn.app.domain.repository

import com.mommydndn.app.domain.model.Banner

interface CommonRepositoy {

    suspend fun getBanners(): List<Banner>
}