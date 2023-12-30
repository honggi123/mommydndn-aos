package com.mommydndn.app.data.network.model.response

import com.mommydndn.app.data.api.model.response.GetEmdItemResponse
import kotlinx.serialization.Serializable

@Serializable
data class GetNearestResponse(
    val emdList: List<GetEmdItemResponse>
)

