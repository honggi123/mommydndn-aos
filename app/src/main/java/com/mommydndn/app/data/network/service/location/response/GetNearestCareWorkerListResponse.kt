package com.mommydndn.app.data.network.service.location.response

import com.mommydndn.app.data.network.service.care.model.CareTypeApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetNearestCareWorkerListResponse = List<NearestCareWorkerApiModel>

@Serializable
data class NearestCareWorkerApiModel(
    @SerialName("jobSeekerId")
    val id: Int,
    @SerialName("caringType")
    val careType: CareTypeApiModel,
    val nickname: String,
    val profileUrl: String,
    val ageAndGender: String
)