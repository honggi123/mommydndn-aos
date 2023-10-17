package com.mommydndn.app.data.api.model

import com.mommydndn.app.data.model.EmdItem
import kotlinx.serialization.Serializable

@Serializable
data class NearestResponse(
    val emdList: List<EmdItem>
)

