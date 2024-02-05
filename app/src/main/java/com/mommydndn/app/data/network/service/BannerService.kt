package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.NetworkBanner

import retrofit2.http.GET

interface BannerService {

    @GET("/api/banner/home")
    suspend fun getBanners(): List<NetworkBanner>
}