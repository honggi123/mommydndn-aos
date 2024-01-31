package com.mommydndn.app.data.mapper

import com.mommydndn.app.data.network.model.NetworkBanner
import com.mommydndn.app.domain.model.Banner

internal fun List<NetworkBanner>.toDomain(): List<Banner> = map {
    Banner(
        id = it.id.toString(),
        imageUrl = it.imageUrl.orEmpty(),
        url = it.url.orEmpty()
    )
}
