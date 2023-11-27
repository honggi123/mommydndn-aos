package com.mommydndn.app.data.api.model.response

import com.mommydndn.app.data.model.map.EmdItem
import kotlinx.serialization.Serializable

@Serializable
data class GetNearestResponse(
    val emdList: List<EmdItem>
)

