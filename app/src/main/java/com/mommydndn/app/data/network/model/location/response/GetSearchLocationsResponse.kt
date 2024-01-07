package com.mommydndn.app.data.network.model.location.response

import com.mommydndn.app.data.network.model.common.LocationApiModel
import com.mommydndn.app.data.network.model.common.MetaApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetSearchLocationsResponse(
    @SerialName("emdList")
    val locations: List<LocationApiModel>,
    @SerialName("meta")
    val meta: MetaApiModel
)