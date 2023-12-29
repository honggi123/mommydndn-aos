package com.mommydndn.app.data.api.model.response


import com.mommydndn.app.domain.model.banner.Banner
import kotlinx.serialization.Serializable

typealias GetBannersResponse = List<GetBannerResponse>

@Serializable
data class GetBannerResponse(
    val bannerId: Int,
    val targetUrl: String?,
    val url: String?
)

fun GetBannersResponse.toDomain(): List<Banner> {
    return this.map {
        Banner(
            bannerId = it.bannerId,
            targetUrl = it.targetUrl ?: "",
            url = it.url ?: ""
        )
    }
}
