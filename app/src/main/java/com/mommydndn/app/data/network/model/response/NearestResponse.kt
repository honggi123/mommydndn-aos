package com.mommydndn.app.data.network.model.response

import com.mommydndn.app.data.model.map.EmdItem
import kotlinx.serialization.Serializable

@Serializable
data class NearestResponse(
    val emdList: List<EmdItem>
)

