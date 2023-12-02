package com.mommydndn.app.data.api.model.response


import com.mommydndn.app.domain.model.banner.Banner
import kotlinx.serialization.Serializable

@Serializable
data class GetBannerResponse(
    val bannerId: Int,
    val targetUrl: String?,
    val url: String?
)

fun GetBannerResponse.toDomain(): Banner {
    return Banner(
        bannerId,
        targetUrl ?: "",
        url ?: ""
    )
}
