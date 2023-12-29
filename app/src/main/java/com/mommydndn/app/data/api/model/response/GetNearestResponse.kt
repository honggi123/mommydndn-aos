package com.mommydndn.app.data.api.model.response

import kotlinx.serialization.Serializable

@Serializable
data class GetNearestResponse(
    val emdList: List<GetEmdItemResponse>
)

