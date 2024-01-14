package com.mommydndn.app.data.network.service.common.response


import com.mommydndn.app.domain.model.banner.Banner
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetBannerListResponse = List<BannerApiModel>

@Serializable
data class BannerApiModel(
    @SerialName("bannerId")
    val id: Int,
    val targetUrl: String?,
    val url: String?
)


