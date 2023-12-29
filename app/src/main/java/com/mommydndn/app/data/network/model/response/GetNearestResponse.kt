package com.mommydndn.app.data.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class GetNearestResponse(
    val emdList: List<GetEmdItemResponse>
)

