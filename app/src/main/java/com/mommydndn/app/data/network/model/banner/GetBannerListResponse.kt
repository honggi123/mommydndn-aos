package com.mommydndn.app.data.api.model.response


import com.mommydndn.app.domain.model.banner.Banner
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetBannerListResponse = List<GetBannerResponse>

@Serializable
data class GetBannerResponse(
    @SerialName("bannerId")
    val id: Int,
    val targetUrl: String?,
    val url: String?
)

fun GetBannerListResponse.toDomain(): List<Banner> {
    return this.map {
        Banner(
            bannerId = it.id,
            targetUrl = it.targetUrl ?: "",
            url = it.url ?: ""
        )
    }
}
